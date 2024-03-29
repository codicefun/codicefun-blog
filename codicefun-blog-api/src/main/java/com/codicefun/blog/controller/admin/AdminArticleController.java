package com.codicefun.blog.controller.admin;

import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.mapper.ArticleMapper;
import com.codicefun.blog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    public AdminArticleController(ArticleService articleService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
    }

    @PostMapping
    public ResponseVo<Object> add(@RequestBody ArticleDto articleDto) {
        return articleService.add(articleDto) ? ResponseVo.success() : ResponseVo.fail();
    }

    @PutMapping("/{id}")
    public ResponseVo<Object> update(@PathVariable Integer id, @RequestBody ArticleDto articleDto) {
        return articleService.updateById(id, articleDto) ? ResponseVo.success() : ResponseVo.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVo<Object> removeById(@PathVariable Integer id) {
        return articleService.removeById(id) ? ResponseVo.success() : ResponseVo.fail();
    }

}
