package com.codicefun.blog.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Integer id;

    private String title;
    private String content;
    private String description;
    private String picture;
    private Integer viewed;
    private Integer liked;
    private Date createTime;
    private Date updateTime;

    private User user;
    private Type type;
    private List<Tag> tagList;
    private List<Comment> commentList;

}
