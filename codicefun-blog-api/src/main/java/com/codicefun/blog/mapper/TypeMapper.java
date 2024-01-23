package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Type;

import java.util.List;
import java.util.Optional;

public interface TypeMapper {

    int insert(Type type);

    Optional<Type> selectById(Integer id);

    List<Type> selectByEquals(Type type);

    int updateById(Type type);

    int deleteById(Integer id);

}
