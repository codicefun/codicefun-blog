package com.codicefun.blog.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private Integer id;
    private String name;

    public static Tag of(Integer id) {
        Tag tag = new Tag();
        tag.id = id;

        return tag;
    }

    public static Tag of(String name) {
        Tag tag = new Tag();
        tag.name = name;

        return tag;
    }

    public static Tag of(Integer id, String name) {
        Tag tag = new Tag();
        tag.id = id;
        tag.name = name;

        return tag;
    }

}
