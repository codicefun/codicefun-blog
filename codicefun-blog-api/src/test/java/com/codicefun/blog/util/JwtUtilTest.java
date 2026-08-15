package com.codicefun.blog.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;

    @Test
    void testGenerateToken() {
        jwtUtil.generateToken("test");
    }

}
