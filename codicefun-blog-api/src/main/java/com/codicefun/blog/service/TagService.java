package com.codicefun.blog.service;

import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.vo.PageVO;

public interface TagService {

    boolean add(Tag tag);

    Tag getById(Integer id);

    PageVO<Tag> getByEquals(Integer current, Integer size, Tag tag);

    boolean updateById(Integer id, Tag tag);

    boolean removeById(Integer id);

}
