package com.codicefun.blog.web;

import com.codicefun.blog.po.Comment;
import com.codicefun.blog.po.User;
import com.codicefun.blog.service.BlogService;
import com.codicefun.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 显示评论列表
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));

        return "blog :: commentList";
    }

    /**
     * 添加评论
     */
    @PostMapping("/comments")
    public String add(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");

        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);

        return "redirect:/comments/" + blogId;
    }

}
