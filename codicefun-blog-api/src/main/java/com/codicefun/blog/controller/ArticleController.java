package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ArticleVO;
import com.codicefun.blog.entity.vo.PageVO;
import com.codicefun.blog.entity.vo.ResponseVO;
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
    public ResponseVO<ArticleVO> getById(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        ArticleVO articleVo = articleMapper.po2vo(article);

        return ResponseVO.success(articleVo);
    }

    @GetMapping
    public ResponseVO<PageVO<ArticleVO>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            ArticleVO articleVO) {
        Article article = articleMapper.vo2po(articleVO);
        Page<Article> page = (Page<Article>) articleService.getBtEquals(current, size, article);
        List<ArticleVO> articleVos = articleMapper.pos2vos(page);
        PageVO<ArticleVO> pageVO = PageVO.of(page.getTotal(), current, size, articleVos);

        return ResponseVO.success(pageVO);
    }

}
