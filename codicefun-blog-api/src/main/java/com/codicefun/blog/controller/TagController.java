package com.codicefun.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.exception.BusinessException;
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
    public ResponseVO<Object> save(@RequestBody Tag tag) {
        return tagService.save(tag) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<Tag> getById(@PathVariable Integer id) {
        Tag tag = tagService.getOptById(id)
                            .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        return ResponseVO.success(tag);
    }

    @GetMapping
    public ResponseVO<PaginationVO<Tag>> listByTerms(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            @RequestBody(required = false) Tag tag) {
        IPage<Tag> page = new Page<>(current, size, true);
        IPage<Tag> result = tagService.listByTerms(page, tag);
        return ResponseVO.page(result);
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> update(@PathVariable Integer id, @RequestBody Tag tag) {
        tag.setId(id);

        return tagService.updateById(tag) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> delete(@PathVariable Integer id) {
        return tagService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
