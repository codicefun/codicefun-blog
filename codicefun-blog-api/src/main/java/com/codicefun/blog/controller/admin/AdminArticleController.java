package com.codicefun.blog.controller.admin;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {

    private final ArticleService articleService;

    public AdminArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseVo<Object> add(@RequestBody Article article) {
        return articleService.add(article) ? ResponseVo.success() : ResponseVo.fail();
    }

    @PutMapping("/{id}")
    public ResponseVo<Object> update(@PathVariable Integer id, @RequestBody Article article) {
        return articleService.updateById(id, article) ? ResponseVo.success() : ResponseVo.fail();
    }

}
