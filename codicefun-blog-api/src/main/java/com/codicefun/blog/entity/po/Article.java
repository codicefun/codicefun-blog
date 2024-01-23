package com.codicefun.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class Article {

    // pk
    private Integer id;

    private String title;
    private String content;
    private String description;
    private String picture;
    private Integer viewed;
    private Integer liked;
    private Date createTime;
    private Date updateTime;

    // fk
    private User user;
    private Type type;

}
