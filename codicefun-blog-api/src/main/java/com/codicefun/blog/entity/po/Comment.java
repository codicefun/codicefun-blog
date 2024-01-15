package com.codicefun.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer id;
    private String nickname;
    private String email;
    private String content;
    private Integer blogId;
    private Integer parentId;
    private Integer liked;
    private Date createTime;

}
