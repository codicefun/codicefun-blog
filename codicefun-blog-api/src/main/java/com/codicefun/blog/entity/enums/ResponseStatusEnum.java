package com.codicefun.blog.entity.enums;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {

    SUCCESS(200, "Request success"),

    FAIL(400, "Request fail"),

    BUSINESS_ERROR(600, "Business error"),
    RESOURCE_NOT_FOUND(601, "Resource not found"),
    INVALID_LOGIN_FORM(602, "Invalid Username or Password ");

    private final int code;
    private final String message;

    ResponseStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
