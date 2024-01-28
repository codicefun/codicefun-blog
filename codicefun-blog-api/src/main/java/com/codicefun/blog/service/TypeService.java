package com.codicefun.blog.service;

import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.PageVo;

import java.util.List;

public interface TypeService {

    boolean add(Type type);

    Type getById(Integer id);

    PageVo<Type> getByEquals(Integer current, Integer size, Type type);

    List<Type> getAll();

    boolean updateById(Integer id, Type type);

    boolean removeById(Integer id);

}
