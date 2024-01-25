package com.codicefun.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String avatar;
    private String role;
    private Date createTime;
    private Date updateTime;

    public static User of(String username) {
        User user = new User();
        user.username = username;

        return user;
    }

}
