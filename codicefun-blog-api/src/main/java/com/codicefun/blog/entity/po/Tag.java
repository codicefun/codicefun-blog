package com.codicefun.blog.entity.po;

import lombok.Data;

@Data
public class Tag {

    private Integer id;
    private String name;

    public static Tag of(String name) {
        Tag tag = new Tag();
        tag.name = name;

        return tag;
    }

}
