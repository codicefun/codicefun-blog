package com.codicefun.blog.service;

import com.codicefun.blog.dao.UserRepository;
import com.codicefun.blog.po.User;
import com.codicefun.blog.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MD5Util.code(password));
    }

}
