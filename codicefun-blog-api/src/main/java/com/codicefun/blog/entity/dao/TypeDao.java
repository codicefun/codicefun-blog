package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Type;

import java.util.List;
import java.util.Optional;

public interface TypeDao {

    int insert(Type type);

    Optional<Type> selectById(Integer id);

    List<Type> selectByEquals(Type type);

    List<Type> selectAll();

    int updateById(Type type);

    int deleteById(Integer id);

}
