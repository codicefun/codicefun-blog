package com.codicefun.blog.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {

    private Long total;
    private Integer current;
    private Integer size;
    private List<T> record;

    public static <T> PageVo<T> of(Long total, Integer current, Integer size, List<T> record) {
        PageVo<T> pageVO = new PageVo<>();
        pageVO.total = total;
        pageVO.current = current;
        pageVO.size = size;
        pageVO.record = record;

        return pageVO;
    }

}
