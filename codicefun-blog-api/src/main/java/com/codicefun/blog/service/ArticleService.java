package com.codicefun.blog.service;

import com.codicefun.blog.entity.po.Article;

import java.util.List;

public interface ArticleService {

    boolean add(Article article);

    Article getById(Integer id);

    List<Article> getBtEquals(Integer current, Integer size, Article article);

    boolean updateById(Integer id, Article article);

}
