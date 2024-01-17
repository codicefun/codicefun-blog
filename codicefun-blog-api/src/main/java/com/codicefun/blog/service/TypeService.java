package com.codicefun.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.blog.entity.po.Type;

public interface TypeService extends IService<Type> {

    IPage<Type> listByTerms(IPage<Type> page, Type type);

}
