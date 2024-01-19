package com.codicefun.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.exception.BusinessException;
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
    public ResponseVO<Object> save(@RequestBody Article article) {
        return articleService.save(article) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<Article> getById(@PathVariable Integer id) {
        Article article = articleService.getOptById(id)
                                        .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        return ResponseVO.success(article);
    }

    @GetMapping
    public ResponseVO<PaginationVO<Article>> listByTerms(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            Article article) {
        IPage<Article> page = new Page<>(current, size, true);
        IPage<Article> result = articleService.listByTerms(page, article);
        return ResponseVO.page(result);
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> update(@PathVariable Integer id, @RequestBody Article article) {
        article.setId(id);

        return articleService.updateById(article) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> delete(@PathVariable Integer id) {
        return articleService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
