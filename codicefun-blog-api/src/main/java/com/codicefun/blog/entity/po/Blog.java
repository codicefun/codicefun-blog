package com.codicefun.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {

    private Integer id;
    private String title;
    private String content;
    private String description;
    private String picture;
    private Integer userId;
    private Integer typeId;
    private Integer viewed;
    private Integer liked;
    private Date createTime;
    private Date updateTime;

}
