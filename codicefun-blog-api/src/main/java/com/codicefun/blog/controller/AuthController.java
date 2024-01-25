package com.codicefun.blog.controller;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.User;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.entity.vo.TokenVo;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.UserService;
import com.codicefun.blog.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseVo<TokenVo> login(@RequestBody User user) {
        String token = userService.login(user);
        TokenVo tokenVO = new TokenVo(token);

        return ResponseVo.success(tokenVO);
    }

    @PostMapping("/validate")
    public ResponseVo<Object> validate(@RequestBody TokenVo tokenVO) {
        if (jwtUtil.validateToken(tokenVO.getToken())) {
            return ResponseVo.success();
        } else {
            throw new BusinessException(ResponseStatusEnum.INVALID_TOKEN);
        }
    }


}
