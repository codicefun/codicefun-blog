package com.codicefun.blog.service;

import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.PageVO;

public interface TypeService {

    boolean add(Type type);

    Type getById(Integer id);

    PageVO<Type> getByEquals(Integer current, Integer size, Type type);

    boolean updateById(Integer id, Type type);

    boolean removeById(Integer id);

}
