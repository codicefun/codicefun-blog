package com.codicefun.blog.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleVo {

    private Integer id;
    private String title;
    private String content;
    private String description;
    private String picture;
    private Integer viewed;
    private Integer liked;
    private Date createTime;
    private Date updateTime;

    private String username;
    private String typeName;

}
