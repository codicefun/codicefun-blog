package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.entity.vo.ResponseVO;
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
    public ResponseVO<Object> add(@RequestBody Type type) {
        return typeService.add(type) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<Type> getById(@PathVariable Integer id) {
        Type type = typeService.getById(id);

        return ResponseVO.success(type);
    }

    @GetMapping
    public ResponseVO<PaginationVO<Type>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            Type type) {
        PaginationVO<Type> paginationVO = typeService.getByEquals(current, size, type);

        return ResponseVO.success(paginationVO);
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> updateById(@PathVariable Integer id, @RequestBody Type type) {
        return typeService.updateById(id, type) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> removeById(@PathVariable Integer id) {
        return typeService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
