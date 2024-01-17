package com.codicefun.blog.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Tag {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

}
