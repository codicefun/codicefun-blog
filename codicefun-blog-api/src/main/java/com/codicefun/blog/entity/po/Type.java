package com.codicefun.blog.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    private Integer id;
    private String name;

    public static Type of(String name) {
        Type type = new Type();
        type.name = name;

        return type;
    }

}
