package com.codicefun.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.blog.entity.po.User;

import java.util.Optional;

public interface UserService extends IService<User> {

    IPage<User> listByTerms(IPage<User> page, User user);

    boolean existsByLogin(User user);

    Optional<User> getOptByLogin(User user);

}
