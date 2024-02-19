package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Comment;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
class CommentDaoTest {

    @Autowired
    CommentDao dao;

    @Test
    void insertTest() {
        // TODO: Generate data use datafaker, see: https://www.datafaker.net/documentation/getting-started/
        Article article = Article.builder()
                                 .id(1)
                                 .build();
        Comment parent = Comment.builder()
                                .id(1)
                                .build();
        Comment comment = Comment.builder()
                                 .email("test@codicefun.com")
                                 .content("Test")
                                 .nickname("Test")
                                 .liked(0)
                                 .article(article)
                                 .parent(parent)
                                 .build();

        assertEquals(1, dao.insert(comment));
    }

    @Test
    void selectByArticleIdTest() {
        List<Comment> commentList = dao.selectByArticleId(1);

        assertEquals(1, commentList.size());
    }

}
