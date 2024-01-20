package com.codicefun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.mapper.UserMapper;
import com.codicefun.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> listByTerms(IPage<User> page, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (user != null) {
            // Add terms
        }

        return page(page, queryWrapper);
    }

    @Override
    public boolean existsByLogin(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (user != null) {
            queryWrapper.eq(User::getUsername, user.getUsername())
                        .eq(User::getPassword, user.getPassword());
            return exists(queryWrapper);
        }

        return false;
    }

    @Override
    public Optional<User> getOptByLogin(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (user != null) {
            queryWrapper.eq(User::getUsername, user.getUsername())
                        .eq(User::getPassword, user.getPassword());
            return getOneOpt(queryWrapper);
        }

        return Optional.empty();
    }

}
