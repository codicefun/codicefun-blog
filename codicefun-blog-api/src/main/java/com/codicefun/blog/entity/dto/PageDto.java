package com.codicefun.blog.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {

    private Long total;
    private Integer current;
    private Integer size;
    private List<T> record;

    public static <T> PageDto<T> of(Long total, Integer current, Integer size, List<T> record) {
        PageDto<T> pageDto = new PageDto<>();
        pageDto.total = total;
        pageDto.current = current;
        pageDto.size = size;
        pageDto.record = record;

        return pageDto;
    }

}
