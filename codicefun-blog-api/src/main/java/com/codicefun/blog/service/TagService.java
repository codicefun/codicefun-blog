package com.codicefun.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.blog.entity.po.Tag;

public interface TagService extends IService<Tag> {

    IPage<Tag> listByTerms(IPage<Tag> page, Tag tag);

}
