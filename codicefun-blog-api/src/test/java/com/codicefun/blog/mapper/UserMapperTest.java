package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    void testInsert() {
        User user = new User("Admin", "admin", "admin", "admin@codicefun.com", "admin", "admin");
        assertEquals(mapper.insert(user), 1);
    }

    @Test
    void testSelectById() {
        System.out.println(mapper.selectById(1));
    }

    @Test
    void testSelectAll() {
        System.out.println(mapper.selectAll());
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1);
        user.setNickname("James");
        assertEquals(mapper.update(user), 1);
    }

    @Test
    void testDelete() {
        assertEquals(mapper.delete(1), 1);
    }

}
