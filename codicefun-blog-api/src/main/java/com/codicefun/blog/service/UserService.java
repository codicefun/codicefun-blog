package com.codicefun.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.blog.entity.po.User;

public interface UserService extends IService<User> {

    IPage<User> listByTerms(IPage<User> page, User user);

}
