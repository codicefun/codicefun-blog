package com.codicefun.blog.service;

import com.codicefun.blog.entity.po.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getById(Integer id);

}
