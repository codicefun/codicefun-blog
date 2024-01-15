package com.codicefun.blog.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tag {

    private Integer id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

}
