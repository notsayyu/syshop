package com.dsy.syshop.core.common.exception;

import com.dsy.syshop.core.common.enums.Code;
import lombok.Getter;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/7 17:45
 */
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -8113387663949085265L;
    private Code code;

    public BusinessException(Code code) {
        this(code, code.getMessage());
    }

    public BusinessException(Code code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
