package com.codicefun.blog.service;

import com.codicefun.blog.entity.dto.PageDto;
import com.codicefun.blog.entity.po.Type;

import java.util.List;

public interface TypeService {

    boolean add(Type type);

    Type getById(Integer id);

    PageDto<Type> getByEquals(Integer current, Integer size, Type type);

    List<Type> getAll();

    boolean updateById(Integer id, Type type);

    boolean removeById(Integer id);

}
