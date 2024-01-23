package com.codicefun.blog.entity.vo;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseVO<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResponseVO<T> success() {
        return success(null);
    }

    public static <T> ResponseVO<T> fail() {
        return fail(null);
    }

    public static <T> ResponseVO<T> success(T data) {
        return respond(ResponseStatusEnum.SUCCESS, data);
    }

    public static <T> ResponseVO<T> fail(T data) {
        return respond(ResponseStatusEnum.FAIL, data);
    }

    public static <T> ResponseVO<T> respond(ResponseStatusEnum status) {
        return ResponseVO.<T>builder()
                         .code(status.getCode())
                         .message(status.getMessage())
                         .build();
    }

    public static <T> ResponseVO<T> respond(ResponseStatusEnum status, T data) {
        return ResponseVO.<T>builder()
                         .code(status.getCode())
                         .message(status.getMessage())
                         .data(data)
                         .build();
    }

    public ResponseVO<T> code(int code) {
        this.setCode(code);

        return this;
    }

    public ResponseVO<T> message(String message) {
        this.setMessage(message);

        return this;
    }

}