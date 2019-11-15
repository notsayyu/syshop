package com.dsy.syshop.core.web.response;

import com.dsy.syshop.core.common.enums.Code;
import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/7 17:10
 */
@Data
@Builder
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;

    public BaseResponse(int code) {
        this(code, null, null);
    }

    public BaseResponse(int code, String message) {
        this(code, message, null);
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Code code) {
        this(code, null);
    }

    public BaseResponse(Code code, T data) {
        this(code.getCode(), code.getMessage(), data);
    }

    public static BaseResponse with(Code code) {
        return with(code, null);
    }

    public static <T> BaseResponse with(Code code, T data) {
        return new BaseResponse<>(code, data);
    }

    public static BaseResponse with(int code) {
        return with(code, null, null);
    }

    public static BaseResponse with(int code, String message) {
        return with(code, message, null);
    }

    public static <T> BaseResponse<T> with(int code, T data) {
        return with(code, null, data);
    }

    public static <T> BaseResponse<T> with(int code, String message, T data) {
        return new BaseResponse<>(code, message, data);
    }
}
