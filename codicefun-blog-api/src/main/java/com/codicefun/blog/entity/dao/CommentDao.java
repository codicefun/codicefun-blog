package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Comment;

import java.util.List;

public interface CommentDao {

    int insert(Comment comment);

    List<Comment> selectAll();

    List<Comment> selectByArticleId(Integer articleId);

    int updateById(Comment comment);

    int deleteById(Integer id);

}
