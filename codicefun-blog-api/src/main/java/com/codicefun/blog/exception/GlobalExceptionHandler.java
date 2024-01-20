package com.codicefun.blog.exception;

import com.codicefun.blog.entity.vo.ResponseVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus
    public ResponseVO<Object> handle(BusinessException e) {
        return ResponseVO.respond(e.getStatusEnum());
    }

}
