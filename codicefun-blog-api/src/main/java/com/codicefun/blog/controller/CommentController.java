package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.dto.PageDto;
import com.codicefun.blog.entity.vo.ResponseVo;
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
    public ResponseVo<Object> add(@RequestBody CommentDto commentDto) {
        return commentService.add(commentDto) ? ResponseVo.success() : ResponseVo.fail();
    }

    @GetMapping("/article/{id}")
    public ResponseVo<PageDto<CommentDto>> getByArticleId(
            @PathVariable Integer id,
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size) {
        PageDto<CommentDto> pageDto = commentService.getByArticleId(id, current, size);

        return ResponseVo.success(pageDto);
    }

}
