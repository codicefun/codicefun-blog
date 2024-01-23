package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseVO<Object> add(@RequestBody Tag tag) {
        return tagService.add(tag) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<Tag> getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);

        return ResponseVO.success(tag);
    }

    @GetMapping
    public ResponseVO<PaginationVO<Tag>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            Tag tag) {
        PaginationVO<Tag> paginationVO = tagService.getByEquals(current, size, tag);

        return ResponseVO.success(paginationVO);
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> updateById(@PathVariable Integer id, @RequestBody Tag tag) {
        return tagService.updateById(id, tag) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> removeById(@PathVariable Integer id) {
        return tagService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
