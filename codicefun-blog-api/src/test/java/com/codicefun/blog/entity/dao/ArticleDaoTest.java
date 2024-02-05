package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
class ArticleDaoTest {

    @Autowired
    ArticleDao dao;

    @Test
    void insertTest() {
        Article article = new Article();
        User user = new User();
        Type type = new Type();
        user.setId(1);
        type.setId(1);
        article.setTitle("Test11");
        article.setContent("Test11 content");
        article.setDescription("Test11 description");
        article.setPicture("https://picsum.photos/320/180");
        article.setUser(user);
        article.setType(type);

        assertEquals(dao.insert(article), 1);
        assertEquals(article.getId(), 11);
    }

    @Test
    void selectByIdTest() {
        Article article = dao.selectById(1)
                             .orElseThrow(BusinessException::notFoundResource);

        assertEquals(article.getTitle(), "Test1");
    }

    @Test
    void selectByEqualsTest() {
        Article article = new Article();
        List<Article> articleList = dao.selectByEquals(article);

        assertEquals(10, articleList.size());
    }

    @Test
    void updateByIdTest() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("New Test");

        assertEquals(1, dao.updateById(article));

        Article found = dao.selectById(1)
                           .orElseThrow(BusinessException::notFoundResource);

        assertEquals("New Test", found.getTitle());
    }

    @Test
    void deleteTagTest() {
        assertEquals(3, dao.deleteTag(1));
    }

    @Test
    void insertTagTest() {
        List<Tag> tagList = List.of(Tag.of(2), Tag.of(3));

        assertEquals(2, dao.insertTag(2, tagList));
    }

}
