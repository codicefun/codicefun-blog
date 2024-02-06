package com.codicefun.blog.entity.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDto {

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
    private String typename;
    private List<String> tagNameList;
    private List<CommentDto> commentDtoList;

}
