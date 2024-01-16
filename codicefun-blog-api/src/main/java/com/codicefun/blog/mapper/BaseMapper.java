package com.codicefun.blog.mapper;

import java.util.List;

public interface BaseMapper<T> {

    Integer insert(T t);

    T selectById(Integer id);

    List<T> selectByTerms(T t);

    Integer update(T t);

    Integer delete(Integer id);

}
