package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ArticleVo;
import com.codicefun.blog.entity.vo.PageVo;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.mapper.ArticleMapper;
import com.codicefun.blog.service.ArticleService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/{id}")
    public ResponseVo<ArticleVo> getById(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        ArticleVo articleVo = articleMapper.po2vo(article);

        return ResponseVo.success(articleVo);
    }

    @GetMapping
    public ResponseVo<PageVo<ArticleVo>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            ArticleVo articleVo) {
        Article article = articleMapper.vo2po(articleVo);
        Page<Article> page = (Page<Article>) articleService.getBtEquals(current, size, article);
        List<ArticleVo> articleVos = articleMapper.pos2vos(page);
        PageVo<ArticleVo> pageVo = PageVo.of(page.getTotal(), current, size, articleVos);

        return ResponseVo.success(pageVo);
    }

}
