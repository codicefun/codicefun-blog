package com.codicefun.blog.service;

import com.codicefun.blog.dao.CommentRepository;
import com.codicefun.blog.po.Comment;
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
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort("createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);

        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        Long parentCommentId = comment.getParentComment().getId();

        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.findOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }

        return commentRepository.save(comment);
    }

    private List<Comment> eachComment(List<Comment> comments) {
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
