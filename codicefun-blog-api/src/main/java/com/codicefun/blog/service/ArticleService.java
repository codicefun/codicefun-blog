package com.codicefun.blog.service;

import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.po.Article;

import java.util.List;

public interface ArticleService {

    boolean add(ArticleDto articleDto);

    Article getById(Integer id);

    List<Article> getBtEquals(Integer current, Integer size, Article article);

    boolean updateById(Integer id, ArticleDto articleDto);

    boolean increaseViewed(Integer id);

    boolean removeById(Integer id);

}
