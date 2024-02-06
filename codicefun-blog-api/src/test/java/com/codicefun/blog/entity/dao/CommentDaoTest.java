package com.codicefun.blog.entity.dao;

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
    void selectByArticleIdTest() {
        List<Comment> commentList = dao.selectByArticleId(1);

        assertEquals(1, commentList.size());
    }

}
