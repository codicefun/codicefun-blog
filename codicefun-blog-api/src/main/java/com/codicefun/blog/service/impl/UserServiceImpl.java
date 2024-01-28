package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.dao.UserDao;
import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.UserService;
import com.codicefun.blog.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserDao userDao, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public boolean exist(User user) {
        return userDao.selectByEquals(user).size() == 1;
    }

    @Override
    public String login(User user) {
        if (exist(user)) {
            return jwtUtil.generateToken(user.getUsername());
        }

        throw new BusinessException(ResponseStatusEnum.INVALID_LOGIN_FORM);
    }

}
