package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> selectById(Integer id);

    List<User> selectByEquals(User user);

}
