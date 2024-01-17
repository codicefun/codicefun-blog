package com.codicefun.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.blog.entity.po.Comment;

public interface CommentService extends IService<Comment> {

    IPage<Comment> listByTerms(IPage<Comment> page, Comment comment);

}
