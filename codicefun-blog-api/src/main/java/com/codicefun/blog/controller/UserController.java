package com.codicefun.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.blog.entity.Constants;
import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseVO<Object> save(@RequestBody User user) {
        return userService.save(user) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping("/{id}")
    public ResponseVO<User> getById(@PathVariable Integer id) {
        User user = userService.getOptById(id)
                               .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        return ResponseVO.success(user);
    }

    @GetMapping
    public ResponseVO<PaginationVO<User>> listByTerms(
            @RequestParam(defaultValue = Constants.PAGE_CURRENT) Integer current,
            @RequestParam(defaultValue = Constants.PAGE_SIZE) Integer size,
            @RequestBody(required = false) User user) {
        IPage<User> page = new Page<>(current, size, true);
        IPage<User> result = userService.listByTerms(page, user);
        return ResponseVO.page(result);
    }

    @PutMapping("/{id}")
    public ResponseVO<Object> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);

        return userService.updateById(user) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> delete(@PathVariable Integer id) {
        return userService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
