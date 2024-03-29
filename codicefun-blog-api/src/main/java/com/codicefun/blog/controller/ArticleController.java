package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.dto.PageDto;
import com.codicefun.blog.entity.po.Article;
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
    public ResponseVo<ArticleDto> getById(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        ArticleDto articleDto = articleMapper.po2dto(article);

        return ResponseVo.success(articleDto);
    }

    @GetMapping
    public ResponseVo<PageDto<ArticleDto>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            ArticleDto articleDto) {
        Article article = articleMapper.dto2po(articleDto);
        Page<Article> page = (Page<Article>) articleService.getBtEquals(current, size, article);
        List<ArticleDto> articleVos = articleMapper.poList2dtoList(page);
        PageDto<ArticleDto> pageDto = PageDto.of(page.getTotal(), current, size, articleVos);

        return ResponseVo.success(pageDto);
    }

    @PutMapping("/{id}/viewed/increase")
    public ResponseVo<Object> increaseViewed(@PathVariable Integer id) {
        return articleService.increaseViewed(id) ? ResponseVo.success() : ResponseVo.fail();
    }

}
