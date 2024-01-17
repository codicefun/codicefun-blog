package com.codicefun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.blog.entity.po.Comment;
import com.codicefun.blog.mapper.CommentMapper;
import com.codicefun.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public IPage<Comment> listByTerms(IPage<Comment> page, Comment comment) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        if (comment != null) {
            // Add terms
        }

        return page(page, queryWrapper);
    }
}
