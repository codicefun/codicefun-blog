package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TypeDaoTest {

    @Autowired
    TypeDao typeDao;

    @Test
    void testInsert() {
        Type type = Type.of("new3");
        System.out.println(typeDao.insert(type));

        System.out.println(type.getId());
    }

}