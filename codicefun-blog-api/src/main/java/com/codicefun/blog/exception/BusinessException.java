package com.codicefun.blog.exception;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private ResponseStatusEnum statusEnum;

    public BusinessException() {
        super(ResponseStatusEnum.BUSINESS_ERROR.getMessage());
        this.statusEnum = ResponseStatusEnum.BUSINESS_ERROR;
    }

    public BusinessException(ResponseStatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.statusEnum = statusEnum;
    }

}
