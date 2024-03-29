package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {

    int insert(Tag tag);

    Optional<Tag> selectById(Integer id);

    Optional<Tag> selectByName(String name);

    List<Tag> selectByEquals(Tag tag);

    List<Tag> selectAll();

    int updateById(Tag tag);

    int deleteById(Integer id);

}
