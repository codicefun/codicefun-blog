package com.codicefun.blog.entity.vo;

import lombok.Data;

@Data
public class PageVO<T> {

    private Integer current = 1;
    private Integer size = 10;
    private Integer total;
    private T data;

}
