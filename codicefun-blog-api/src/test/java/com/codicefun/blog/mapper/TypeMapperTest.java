package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TypeMapperTest {

    @Autowired
    TypeMapper mapper;

    @Test
    void testInsert() {
        Type type = new Type();
        type.setName("后端");

        assertEquals(mapper.insert(type), 1);
    }

    @Test
    void testSelectById() {
        Type type = mapper.selectById(1)
                          .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        System.out.println(type);
    }

    @Test
    void testSelectByEqual() {
        Type type = new Type();
        type.setName("后端");

        List<Type> typeList = mapper.selectByEquals(type);
        System.out.println(typeList);
    }

    @Test
    void testUpdateById() {
        Type type = mapper.selectById(3)
                          .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));
        type.setName("前端");

        assertEquals(mapper.updateById(type), 1);
    }

    @Test
    void testDelete() {
        assertEquals(mapper.deleteById(3), 1);
    }

}
