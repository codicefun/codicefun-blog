package com.codicefun.blog.controller.admin;

import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tag")
public class AdminTagController {

    private final TagService tagService;

    public AdminTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseVo<Object> add(@RequestBody Tag tag) {
        return tagService.add(tag) ? ResponseVo.success() : ResponseVo.fail();
    }

    @PutMapping("/{id}")
    public ResponseVo<Object> updateById(@PathVariable Integer id, @RequestBody Tag tag) {
        return tagService.updateById(id, tag) ? ResponseVo.success() : ResponseVo.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVo<Object> removeById(@PathVariable Integer id) {
        return tagService.removeById(id) ? ResponseVo.success() : ResponseVo.fail();
    }

}
