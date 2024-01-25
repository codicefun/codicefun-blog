package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ArticleVO;
import com.codicefun.blog.entity.vo.PageVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.service.ArticleService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseVO<ArticleVO> getById(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        ArticleVO articleVO = ArticleVO.po2vo(article);

        return ResponseVO.success(articleVO);
    }

    @GetMapping
    public ResponseVO<PageVO<ArticleVO>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            ArticleVO articleVO) {
        Article article = ArticleVO.vo2po(articleVO);
        Page<Article> page = (Page<Article>) articleService.getBtEquals(current, size, article);
        List<ArticleVO> articleVOList = new ArrayList<>();

        for (Article item: page) {
            articleVOList.add(ArticleVO.po2vo(item));
        }

        PageVO<ArticleVO> pageVO = PageVO.of(page.getTotal(), current, size, articleVOList);

        return ResponseVO.success(pageVO);
    }

}
