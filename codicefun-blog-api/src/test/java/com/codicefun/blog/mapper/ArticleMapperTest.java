package com.codicefun.blog.mapper;

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
    }

}
