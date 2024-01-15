package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TagMapperTest {

    @Autowired
    TagMapper mapper;

    @Test
    void testInsert() {
        int rows = mapper.insert(new Tag("Java"));

        assertEquals(rows, 1);
    }

    @Test
    void testSelectById() {
        Tag tag = mapper.selectById(1);
        System.out.println(tag);
    }

}
