package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.dao.ArticleDao;
import com.codicefun.blog.entity.dao.TagDao;
import com.codicefun.blog.entity.dao.TypeDao;
import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.mapper.ArticleMapper;
import com.codicefun.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;
    private final TypeDao typeDao;
    private final TagDao tagDao;

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleDao articleDao, TypeDao typeDao, TagDao tagDao, ArticleMapper articleMapper) {
        this.articleDao = articleDao;
        this.typeDao = typeDao;
        this.tagDao = tagDao;
        this.articleMapper = articleMapper;
    }

    @Transactional
    @Override
    public boolean add(ArticleDto articleDto) {
        Article article = articleMapper.dto2po(articleDto);
        // Admin only
        article.setUser(User.admin());
        updateType(articleDto, article);
        articleDao.insert(article);
        updateTag(article.getId(), articleDto.getTagNameList());

        return true;
    }

    @Override
    public Article getById(Integer id) {
        return articleDao.selectById(id)
                         .orElseThrow(BusinessException::notFoundResource);
    }

    @Override
    public List<Article> getBtEquals(Integer current, Integer size, Article article) {
        PageHelper.startPage(current, size);

        return articleDao.selectByEquals(article);
    }

    @Transactional
    @Override
    public boolean updateById(Integer id, ArticleDto articleDto) {
        Article article = articleMapper.dto2po(articleDto);
        article.setId(id);
        updateType(articleDto, article);
        updateTag(article.getId(), articleDto.getTagNameList());
        articleDao.updateById(article);

        return true;
    }

    private void updateType(ArticleDto articleDto, Article article) {
        // get type
        String typename = articleDto.getTypename();
        Type type = typeDao.selectByName(typename)
                           .orElseGet(() -> {
                               // If not found, create new type
                               Type t = Type.of(typename);
                               typeDao.insert(t);
                               return t;
                           });

        // update type
        article.setType(type);
    }

    private void updateTag(int articleId, List<String> tagNameList) {
        // delete tag
        articleDao.deleteTag(articleId);

        // get tag
        List<Tag> tagList = new ArrayList<>();
        for (String tagName : tagNameList) {
            Tag tag = tagDao.selectByName(tagName)
                            .orElseGet(() -> {
                                // If not found, create new tag
                                Tag t = Tag.of(tagName);
                                tagDao.insert(t);
                                return t;
                            });
            tagList.add(tag);
        }

        // add tag
        articleDao.insertTag(articleId, tagList);
    }

    @Transactional
    @Override
    public boolean increaseViewed(Integer id) {
        return articleDao.updateViewed(id) == 1;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        return articleDao.deleteById(id) == 1;
    }

}
