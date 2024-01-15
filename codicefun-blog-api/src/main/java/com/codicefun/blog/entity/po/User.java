package com.codicefun.blog.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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

    public User(String username, String password, String nickname, String email, String avatar, String role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.avatar = avatar;
        this.role = role;
    }

}
