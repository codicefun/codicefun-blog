package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
class TypeDaoTest {

    @Autowired
    TypeDao dao;

    @Test
    void testInsert() {
        Type type = Type.of("New");

        assertEquals(1, dao.insert(type));
        assertEquals(4, type.getId());
    }

    @Test
    void testSelectById() {
        Type type = dao.selectById(1)
                       .orElseThrow(BusinessException::notFoundResource);

        assertEquals("Test", type.getName());
    }

    @Test
    void testSelectByName() {
        Type type = dao.selectByName("Test")
                       .orElseThrow(BusinessException::notFoundResource);

        assertEquals(1, type.getId());
    }

    @Test
    void testSelectByEquals() {
        Type type = Type.of("Test");
        List<Type> typeList = dao.selectByEquals(type);

        assertEquals(1, typeList.size());
        assertEquals(1, typeList.get(0).getId());
    }

    @Test
    void testSelectAll() {
        List<Type> typeList = dao.selectAll();

        assertEquals(3, typeList.size());
    }

    @Test
    void testUpdateById() {
        Type type = new Type();
        type.setId(1);
        type.setName("New Test");

        assertEquals(1, dao.updateById(type));

        Type updatedType = dao.selectById(1)
                              .orElseThrow(BusinessException::notFoundResource);

        assertEquals("New Test", updatedType.getName());
    }

    @Test
    void testDeleteById() {
        assertEquals(1, dao.deleteById(1));
        assertEquals(Optional.empty(), dao.selectById(1));
    }

}
