package com.codicefun.blog.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static User admin() {
        User user = new User();
        user.id = 1;
        user.username = "admin";

        return user;
    }

}
