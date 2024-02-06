package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
class ArticleMapperTest {

    @Autowired
    ArticleMapper mapper;

    @Test
    void po2dtoTest() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("Test");
        article.setContent("Test Content");
        article.setDescription("Test Description");
        article.setPicture("Test Picture");
    }

}
