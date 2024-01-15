package com.codicefun.blog.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Type {

    private Integer id;
    private String name;

    public Type(String name) {
        this.name = name;
    }

}
