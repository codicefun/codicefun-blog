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
    public ResponseVO<TokenVO> login(@RequestBody User user) {
        String token = userService.login(user);
        TokenVO tokenVO = new TokenVO(token);

        return ResponseVO.success(tokenVO);
    }

    @PostMapping("/validate")
    public ResponseVO<Object> validate(@RequestBody TokenVO tokenVO) {
        if (jwtUtil.validateToken(tokenVO.getToken())) {
            return ResponseVO.success();
        } else {
            throw new BusinessException(ResponseStatusEnum.INVALID_TOKEN);
        }
    }


}
