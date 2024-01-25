package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.dao.ArticleDao;
import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleMapper;

    public ArticleServiceImpl(ArticleDao articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public boolean add(Article article) {
        return articleMapper.insert(article) == 1;
    }

    @Override
    public Article getById(Integer id) {
        return articleMapper.selectById(id)
                            .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));
    }

    @Override
    public List<Article> getBtEquals(Integer current, Integer size, Article article) {
        PageHelper.startPage(current, size);

        return articleMapper.selectByEquals(article);
    }

    @Override
    public boolean updateById(Integer id, Article article) {
        article.setId(id);

        return articleMapper.updateById(article) == 1;
    }

}
