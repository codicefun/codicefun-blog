package com.codicefun.blog.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String nickname;

    private String email;

    private String content;

    private Integer articleId;

    private Integer parentId;

    private Integer liked;

    private Date createTime;

}
