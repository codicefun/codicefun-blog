package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
class TagDaoTest {

    @Autowired
    TagDao dao;

    @Test
    void insertTest() {
        Tag tag = Tag.of("New");

        assertEquals(1, dao.insert(tag));
        assertEquals(4, tag.getId());
    }

    @Test
    void selectByIdTest() {
        Tag tag = dao.selectById(1)
                     .orElseThrow(BusinessException::notFoundResource);

        assertEquals("Test", tag.getName());
    }

    @Test
    void selectByName() {
        Tag tag = dao.selectByName("Test")
                     .orElseThrow(BusinessException::notFoundResource);

        assertEquals(1, tag.getId());
    }

    @Test
    void selectByEquals() {
        Tag tag = Tag.of("Test");
        List<Tag> tagList = dao.selectByEquals(tag);

        assertEquals(1, tagList.size());
        assertEquals(1, tagList.get(0).getId());
    }

    @Test
    void selectAll() {
        List<Tag> tagList = dao.selectAll();

        assertEquals(3, tagList.size());
    }

    @Test
    void updateById() {
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("New Test");

        assertEquals(1, dao.updateById(tag));

        Tag found = dao.selectById(1)
                       .orElseThrow(BusinessException::notFoundResource);

        assertEquals("New Test", found.getName());
    }

    @Test
    void deleteById() {
        assertEquals(1, dao.deleteById(1));
        assertEquals(Optional.empty(), dao.selectById(1));
    }

}