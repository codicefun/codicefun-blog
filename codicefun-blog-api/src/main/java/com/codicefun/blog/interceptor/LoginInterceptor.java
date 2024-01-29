package com.codicefun.blog.interceptor;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.vo.ResponseVo;
import com.codicefun.blog.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public LoginInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String token = request.getHeader("Token");
        boolean isValidated = jwtUtil.validateToken(token);

        if (!isValidated) {
            ResponseVo<Object> responseVO = ResponseVo.respond(ResponseStatusEnum.INVALID_TOKEN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(responseVO);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().print(json);
            return false;
        }

        return true;
    }

}
