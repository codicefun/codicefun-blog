package com.codicefun.blog.service;

import com.codicefun.blog.entity.po.Article;

public interface ArticleService {

    boolean add(Article article);

    Article getById(Integer id);

}
