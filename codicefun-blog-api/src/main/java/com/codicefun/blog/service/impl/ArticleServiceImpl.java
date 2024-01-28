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

    private final ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public boolean add(Article article) {
        return articleDao.insert(article) == 1;
    }

    @Override
    public Article getById(Integer id) {
        return articleDao.selectById(id)
                         .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));
    }

    @Override
    public List<Article> getBtEquals(Integer current, Integer size, Article article) {
        PageHelper.startPage(current, size);

        return articleDao.selectByEquals(article);
    }

    @Override
    public boolean updateById(Integer id, Article article) {
        article.setId(id);

        return articleDao.updateById(article) == 1;
    }

}
