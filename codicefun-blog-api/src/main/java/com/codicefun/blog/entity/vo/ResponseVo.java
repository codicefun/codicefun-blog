package com.codicefun.blog.entity.vo;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseVo<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResponseVo<T> success() {
        return success(null);
    }

    public static <T> ResponseVo<T> fail() {
        return fail(null);
    }

    public static <T> ResponseVo<T> success(T data) {
        return respond(ResponseStatusEnum.SUCCESS, data);
    }

    public static <T> ResponseVo<T> fail(T data) {
        return respond(ResponseStatusEnum.FAIL, data);
    }

    public static <T> ResponseVo<T> respond(ResponseStatusEnum status) {
        return ResponseVo.<T>builder()
                         .code(status.getCode())
                         .message(status.getMessage())
                         .build();
    }

    public static <T> ResponseVo<T> respond(ResponseStatusEnum status, T data) {
        return ResponseVo.<T>builder()
                         .code(status.getCode())
                         .message(status.getMessage())
                         .data(data)
                         .build();
    }

    public ResponseVo<T> code(int code) {
        this.setCode(code);

        return this;
    }

    public ResponseVo<T> message(String message) {
        this.setMessage(message);

        return this;
    }

}