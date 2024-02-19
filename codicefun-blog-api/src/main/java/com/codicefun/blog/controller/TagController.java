package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.dto.PageDto;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public ResponseVo<Tag> getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);

        return ResponseVo.success(tag);
    }

    @GetMapping("/page")
    public ResponseVo<PageDto<Tag>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            Tag tag) {
        PageDto<Tag> pageDto = tagService.getByEquals(current, size, tag);

        return ResponseVo.success(pageDto);
    }

    @GetMapping("/all")
    public ResponseVo<List<Tag>> getAll() {
        List<Tag> tagList = tagService.getAll();

        return ResponseVo.success(tagList);
    }

}
