package com.codicefun.blog.service;

import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.vo.PageVo;

import java.util.List;

public interface TagService {

    boolean add(Tag tag);

    Tag getById(Integer id);

    PageVo<Tag> getByEquals(Integer current, Integer size, Tag tag);

    List<Tag> getAll();

    boolean updateById(Integer id, Tag tag);

    boolean removeById(Integer id);

}
