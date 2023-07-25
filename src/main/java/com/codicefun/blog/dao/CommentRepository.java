package com.codicefun.blog.dao;

import com.codicefun.blog.model.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 根据 blog id 查询没有父级评论的评论
     *
     * @param blogId 博客 id
     * @param sort   排序规则
     * @return 评论列表
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

}
