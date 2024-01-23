package com.codicefun.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationVO<T> {

    private Long total;
    private Integer current;
    private Integer size;
    private List<T> list;

}
