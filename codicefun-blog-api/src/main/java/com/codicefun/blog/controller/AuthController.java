package com.codicefun.blog.controller;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.entity.vo.ResponseVO;
import com.codicefun.blog.entity.vo.TokenVO;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.UserService;
import com.codicefun.blog.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseVO<TokenVO> login(@RequestBody User user) {
        User loginUser = userService.getOptByLogin(user)
                                    .orElseThrow(() -> new BusinessException(ResponseStatusEnum.INVALID_LOGIN_FORM));
        String token = jwtUtil.generateToken(loginUser.getUsername());

        return ResponseVO.success(new TokenVO(token));
    }

}
