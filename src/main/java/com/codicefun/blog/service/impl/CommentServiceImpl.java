package com.codicefun.blog.service.impl;

import com.codicefun.blog.dao.CommentRepository;
import com.codicefun.blog.model.pojo.Comment;
import com.codicefun.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentRepository commentRepository;
    private List<Comment> tempReplies = new ArrayList<>();

    @Override
    public List<Comment> listByBlog(Long blogId) {
        Sort sort = new Sort("createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);

        return eachComment(comments);
    }

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        Long parentCommentId = comment.getParentComment().getId();

        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.findOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }

        commentRepository.save(comment);
    }

    /**
     * 将多级评论转为两级评论
     *
     * @param comments 多级评论列表
     * @return 两级评论列表
     */
    private List<Comment> eachComment(List<Comment> comments) {
        // 放置拷贝的评论对象，不改变数据库中的结构
        List<Comment> commentsView = new ArrayList<>();

        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }

        // 合并评论的各层子代到一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * 合并各层子评论到一级子评论中
     *
     * @param comments 多级评论列表
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                // 循环迭代，找出子代，存放在 tempReplies 中
                recursively(reply);
            }
            // 修改顶级节点的 reply 集合为迭代处理后的集合
            comment.setReplyComments(tempReplies);
            // 清除临时存放区
            tempReplies = new ArrayList<>();
        }
    }

    /**
     * 递归找出所有子评论，并放入临时列表中
     *
     * @param comment 当前评论
     */
    private void recursively(Comment comment) {
        tempReplies.add(comment); // 顶节点添加到临时存放集合

        if (comment.getReplyComments().size() > 0) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                tempReplies.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(comment);
                }
            }
        }
    }

}
