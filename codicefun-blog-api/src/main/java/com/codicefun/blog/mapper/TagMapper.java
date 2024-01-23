package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Tag;

import java.util.List;
import java.util.Optional;

public interface TagMapper {

    int insert(Tag tag);

    Optional<Tag> selectById(Integer id);

    List<Tag> selectByEquals(Tag tag);

    int updateById(Tag tag);

    int deleteById(Integer id);

}
