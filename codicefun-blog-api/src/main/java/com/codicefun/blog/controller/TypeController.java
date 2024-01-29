package com.codicefun.blog.controller;

import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.PageVo;
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

    @PostMapping
    public ResponseVo<Object> add(@RequestBody Type type) {
        return typeService.add(type) ? ResponseVo.success() : ResponseVo.fail();
    }

    @GetMapping("/{id}")
    public ResponseVo<Type> getById(@PathVariable Integer id) {
        Type type = typeService.getById(id);

        return ResponseVo.success(type);
    }

    @GetMapping("/page")
    public ResponseVo<PageVo<Type>> getByEquals(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            Type type) {
        PageVo<Type> pageVo = typeService.getByEquals(current, size, type);

        return ResponseVo.success(pageVo);
    }

    @GetMapping("/all")
    public ResponseVo<List<Type>> getAll() {
        List<Type> typeList = typeService.getAll();

        return ResponseVo.success(typeList);
    }

    @PutMapping("/{id}")
    public ResponseVo<Object> updateById(@PathVariable Integer id, @RequestBody Type type) {
        return typeService.updateById(id, type) ? ResponseVo.success() : ResponseVo.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVo<Object> removeById(@PathVariable Integer id) {
        return typeService.removeById(id) ? ResponseVo.success() : ResponseVo.fail();
    }

}
