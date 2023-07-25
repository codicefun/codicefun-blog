package com.codicefun.blog.service;

import com.codicefun.blog.model.pojo.User;

public interface UserService {

    /**
     * 校验用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 通过校验的用户对象
     */
    User checkUser(String username, String password);

}
