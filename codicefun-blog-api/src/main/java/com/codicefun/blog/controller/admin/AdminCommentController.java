package com.codicefun.blog.controller.admin;

import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {

    private final CommentService commentService;

    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PutMapping("/{id}")
    public ResponseVo<Object> update(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        return commentService.updateById(id, commentDto) ? ResponseVo.success() : ResponseVo.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVo<Object> remove(@PathVariable Integer id) {
        return commentService.removeById(id) ? ResponseVo.success() : ResponseVo.fail();
    }

}
