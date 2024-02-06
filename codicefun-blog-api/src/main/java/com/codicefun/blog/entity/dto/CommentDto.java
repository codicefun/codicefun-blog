package com.codicefun.blog.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Integer id;

    private String nickname;
    private String email;
    private String content;
    private Integer liked;
    private Date createTime;

    private Integer articleId;
    private Integer parentId;

}
