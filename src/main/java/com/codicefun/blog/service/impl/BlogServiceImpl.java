package com.codicefun.blog.service.impl;

import com.codicefun.blog.dao.BlogRepository;
import com.codicefun.blog.exception.NotFoundException;
import com.codicefun.blog.model.pojo.Blog;
import com.codicefun.blog.model.vo.BlogQuery;
import com.codicefun.blog.service.BlogService;
import com.codicefun.blog.util.MarkdownUtils;
import com.codicefun.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Blog getContent(Long id) {
        Blog blog = blogRepository.findOne(id);

        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }

        return convertMarkdownToHtml(blog);
    }

    @Override
    public Page<Blog> listPublished(Pageable pageable) {
        return blogRepository.findByPublishedTrue(pageable);
    }

    @Override
    public List<Blog> listRecommended(int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(0, size, sort);

        return blogRepository.findByRecommendTrueAndPublishedTrue(pageable);
    }

    @Override
    public Page<Blog> listByMultiQuery(BlogQuery query, Pageable pageable) {
        return blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.getTitle() != null && !query.getTitle().equals("")) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + query.getTitle() + "%"));
            }
            if (query.getTypeId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type").get("id"), query.getTypeId()));
            }
            if (query.isRecommend()) {
                predicates.add(criteriaBuilder.equal(root.get("recommend"), query.isRecommend()));
            }
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return null;
        }, pageable);
    }

    @Override
    public Page<Blog> listByQuery(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    @Override
    public Page<Blog> listByType(Long typeId, Pageable pageable) {
        return blogRepository.findByTypeId(typeId, pageable);
    }

    @Override
    public Page<Blog> listByTag(Long tagId, Pageable pageable) {
        return blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Join<Object, Object> join = root.join("tags");
            return criteriaBuilder.equal(join.get("id"), tagId);
        }, pageable);
    }

    @Override
    public Map<String, List<Blog>> getArchive() {
        List<String> years = blogRepository.findByGroupYear();
        Map<String, List<Blog>> map = new TreeMap<>(Comparator.reverseOrder());

        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }

        return map;
    }

    @Override
    public Long countPublished() {
        return blogRepository.countByPublishedTrue();
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        // 设置 Blog 信息
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findOne(id);

        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }

        // ignore null properties
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());

        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }

    /**
     * 将博客内容的 markdown 格式转为 html 格式，不改变数据库中的博客内容
     *
     * @param blog 内容为 markdown 格式的博客对象
     * @return 内容为 html 格式的博客对象
     */
    private Blog convertMarkdownToHtml(Blog blog) {
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(b.getId());

        return b;
    }

}
