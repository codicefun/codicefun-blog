package com.codicefun.blog.exception;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private ResponseStatusEnum status;

    public BusinessException() {
        super(ResponseStatusEnum.BUSINESS_ERROR.getMessage());
        this.status = ResponseStatusEnum.BUSINESS_ERROR;
    }

    public BusinessException(ResponseStatusEnum status) {
        super(status.getMessage());
        this.status = status;
    }

}
