package com.codicefun.blog.controller;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ArticleVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseVO<Object> add(@RequestBody Article article) {
        return articleService.add(article) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<ArticleVO> getById(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        ArticleVO articleVO = ArticleVO.of(article);

        return ResponseVO.success(articleVO);
    }

}
