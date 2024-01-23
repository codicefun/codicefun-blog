package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.mapper.UserMapper;
import com.codicefun.blog.service.UserService;
import com.codicefun.blog.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserMapper userMapper, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean exist(User user) {
        return userMapper.selectByEquals(user).size() == 1;
    }

    @Override
    public String login(User user) {
        if (exist(user)) {
            return jwtUtil.generateToken(user.getUsername());
        }

        throw new BusinessException(ResponseStatusEnum.INVALID_LOGIN_FORM);
    }

}
