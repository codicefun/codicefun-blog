package com.codicefun.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.blog.entity.po.Article;

public interface ArticleService extends IService<Article> {

    IPage<Article> listByTerms(IPage<Article> page, Article article);

}
