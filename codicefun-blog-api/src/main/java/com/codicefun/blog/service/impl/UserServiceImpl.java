package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.mapper.UserMapper;
import com.codicefun.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userMapper.selectById(id);
    }

}
