package com.codicefun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.mapper.ArticleMapper;
import com.codicefun.blog.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public IPage<Article> listByTerms(IPage<Article> page, Article article) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        if (article != null) {
            // Add terms
        }

        return page(page, queryWrapper);
    }
}
