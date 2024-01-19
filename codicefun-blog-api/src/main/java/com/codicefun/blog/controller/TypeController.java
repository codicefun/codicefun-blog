package com.codicefun.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.TypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping
    public ResponseVO<Object> save(@RequestBody Type type) {
        return typeService.save(type) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<Type> getById(@PathVariable Integer id) {
        Type type = typeService.getOptById(id)
                               .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        return ResponseVO.success(type);
    }

    @GetMapping
    public ResponseVO<PaginationVO<Type>> listByTerms(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            Type type) {
        IPage<Type> page = new Page<>(current, size, true);
        IPage<Type> result = typeService.listByTerms(page, type);
        return ResponseVO.page(result);
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> update(@PathVariable Integer id, @RequestBody Type type) {
        type.setId(id);

        return typeService.updateById(type) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> delete(@PathVariable Integer id) {
        return typeService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
