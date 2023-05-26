package com.codicefun.blog.service;

import com.codicefun.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);

}
