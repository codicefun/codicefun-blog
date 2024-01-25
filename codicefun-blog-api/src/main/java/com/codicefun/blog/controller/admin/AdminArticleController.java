package com.codicefun.blog.controller.admin;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ResponseVO;
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
    public ResponseVO<Object> add(@RequestBody Article article) {
        return articleService.add(article) ? ResponseVO.success() : ResponseVO.fail();
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> update(@PathVariable Integer id, @RequestBody Article article) {
        return articleService.updateById(id, article) ? ResponseVO.success() : ResponseVO.fail();
    }

}
