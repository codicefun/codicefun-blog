package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.User;

import java.util.List;
import java.util.Optional;

public interface UserMapper {

    Optional<User> selectById(Integer id);

    List<User> selectByEquals(User user);

}
