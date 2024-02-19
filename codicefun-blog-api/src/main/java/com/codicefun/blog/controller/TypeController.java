package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.dto.PageDto;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/{id}")
    public ResponseVo<Type> getById(@PathVariable Integer id) {
        Type type = typeService.getById(id);

        return ResponseVo.success(type);
    }

    @GetMapping("/page")
    public ResponseVo<PageDto<Type>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            Type type) {
        PageDto<Type> pageDto = typeService.getByEquals(current, size, type);

        return ResponseVo.success(pageDto);
    }

    @GetMapping("/all")
    public ResponseVo<List<Type>> getAll() {
        List<Type> typeList = typeService.getAll();

        return ResponseVo.success(typeList);
    }

}
