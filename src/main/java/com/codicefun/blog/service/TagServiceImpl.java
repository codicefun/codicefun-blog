package com.codicefun.blog.service;

import com.codicefun.blog.dao.TagRepository;
import com.codicefun.blog.exception.NotFoundException;
import com.codicefun.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    /**
     * find tags by tag id string
     *
     * @param ids tag ids string("1,2,3")
     * @return find result
     */
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAll(tagIds2TagList(ids));
    }

    /**
     * convert tag ids string("1,2,3") to tag list
     *
     * @param ids tag ids string("1,2,3")
     * @return tag list
     */
    private List<Long> tagIds2TagList(String ids) {
        ArrayList<Long> list = new ArrayList<>();

        if (ids != null && !ids.equals("")) {
            String[] split = ids.split(",");
            for (String s : split) {
                try {
                    list.add(new Long(s));
                } catch (NumberFormatException e) { // auto add new tag
                    Tag tag = new Tag();
                    tag.setName(s);
                    // noinspection SpringTransactionalMethodCallsInspection
                    saveTag(tag);
                    list.add(tag.getId());
                }
            }
        }

        return list;
    }

    @Override
    public List<Tag> listTag(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);

        return tagRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findOne(id);

        if (t == null) {
            throw new NotFoundException("不存在该分类");
        }

        BeanUtils.copyProperties(tag, t);

        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }
}
