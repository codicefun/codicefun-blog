package com.codicefun.blog.controller;

import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.entity.vo.TokenVO;
import com.codicefun.blog.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseVO<TokenVO> login(@RequestBody User user) {
        String token = userService.login(user);
        TokenVO tokenVO = new TokenVO(token);

        return ResponseVO.success(tokenVO);
    }

}
