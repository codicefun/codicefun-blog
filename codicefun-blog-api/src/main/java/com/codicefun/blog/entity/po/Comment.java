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
public class Comment {

    private Integer id;

    private String nickname;
    private String email;
    private String content;
    private Integer liked;
    private Date createTime;

    private Article article;
    private Comment parent;

}
