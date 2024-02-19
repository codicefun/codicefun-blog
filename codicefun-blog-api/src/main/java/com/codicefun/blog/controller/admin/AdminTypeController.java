package com.codicefun.blog.controller.admin;

import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.service.TypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/type")
public class AdminTypeController {

    private final TypeService typeService;

    public AdminTypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping
    public ResponseVo<Object> add(@RequestBody Type type) {
        return typeService.add(type) ? ResponseVo.success() : ResponseVo.fail();
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
