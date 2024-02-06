package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> selectByArticleId(Integer articleId);

}
