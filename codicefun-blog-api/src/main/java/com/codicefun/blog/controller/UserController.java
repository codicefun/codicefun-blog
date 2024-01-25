package com.codicefun.blog.controller;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseVo<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id)
                               .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));

        return ResponseVo.success(user);
    }

}
