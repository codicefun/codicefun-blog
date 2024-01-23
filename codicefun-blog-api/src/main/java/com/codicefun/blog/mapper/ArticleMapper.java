package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleMapper {

    int insert(Article article);

    Optional<Article> selectById(Integer id);

    List<Article> selectByEquals(Article article);

}
