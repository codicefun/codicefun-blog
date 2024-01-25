package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {

    int insert(Article article);

    Optional<Article> selectById(Integer id);

    List<Article> selectByEquals(Article article);

    int updateById(Article article);

}
