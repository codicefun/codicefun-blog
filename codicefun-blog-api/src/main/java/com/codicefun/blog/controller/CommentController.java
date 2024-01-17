package com.codicefun.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Comment;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseVO<Object> save(@RequestBody Comment comment) {
        return commentService.save(comment) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<Comment> getById(@PathVariable Integer id) {
        Comment comment = commentService.getOptById(id)
                                        .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        return ResponseVO.success(comment);
    }

    @GetMapping
    public ResponseVO<PaginationVO<Comment>> listByTerms(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            @RequestBody(required = false) Comment comment) {
        IPage<Comment> page = new Page<>(current, size, true);
        IPage<Comment> result = commentService.listByTerms(page, comment);
        return ResponseVO.page(result);
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> update(@PathVariable Integer id, @RequestBody Comment comment) {
        comment.setId(id);

        return commentService.updateById(comment) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> delete(@PathVariable Integer id) {
        return commentService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
