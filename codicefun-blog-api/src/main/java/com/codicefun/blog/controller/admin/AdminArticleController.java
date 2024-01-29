package com.codicefun.blog.controller.admin;

import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.po.Article;
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
    public ResponseVo<Object> add(@RequestBody Article article) {
        return articleService.add(article) ? ResponseVo.success() : ResponseVo.fail();
    }

    @PutMapping("/{id}")
    public ResponseVo<Object> update(@PathVariable Integer id, @RequestBody ArticleDto articleDto) {
        return articleService.updateById(id, articleDto) ? ResponseVo.success() : ResponseVo.fail();
    }

}
