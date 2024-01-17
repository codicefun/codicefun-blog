package com.codicefun.blog.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Article {

    @TableId(type = IdType.AUTO)
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
