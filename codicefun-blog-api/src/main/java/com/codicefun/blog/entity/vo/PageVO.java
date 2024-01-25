package com.codicefun.blog.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {

    private Long total;
    private Integer current;
    private Integer size;
    private List<T> record;

    public static <T> PageVO<T> of(Long total, Integer current, Integer size, List<T> record) {
        PageVO<T> pageVO = new PageVO<>();
        pageVO.total = total;
        pageVO.current = current;
        pageVO.size = size;
        pageVO.record = record;

        return pageVO;
    }

}
