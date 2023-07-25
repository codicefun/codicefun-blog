package com.codicefun.blog.service;

import com.codicefun.blog.model.pojo.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 列出一篇博客下的所有评论
     *
     * @param blogId 博客 id
     * @return 评论列表
     */
    List<Comment> listByBlog(Long blogId);

    /**
     * 保存评论
     *
     * @param comment 待保存的评论对象
     */
    void saveComment(Comment comment);

}
