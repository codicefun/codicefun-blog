package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    void testSelectById() {
        User user = mapper.selectById(1)
                          .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        System.out.println(user);
    }

}
