package com.codicefun.blog.entity.po;

import lombok.Data;

@Data
public class Type {

    private Integer id;
    private String name;

    public static Type of(String name) {
        Type type = new Type();
        type.name = name;

        return type;
    }

}
