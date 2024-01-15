package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Tag;

import java.util.List;

public interface TagMapper {

    int insert(Tag tag);

    Tag selectById(Integer id);

    List<Tag> selectAll();

    int update(Tag tag);

    int delete(Integer id);

}
