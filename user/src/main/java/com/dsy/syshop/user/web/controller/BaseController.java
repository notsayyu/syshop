package com.dsy.syshop.user.web.controller;

import com.dsy.syshop.core.common.enums.Code;
import com.dsy.syshop.core.web.response.BasePageableResponse;
import com.dsy.syshop.core.web.response.BaseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 14:45
 */
public abstract class BaseController {
    protected <T> BaseResponse<T> response(T data) {
        return BaseResponse.with(Code.SUCCESS, data);
    }

    protected <T> BaseResponse<T> response(Code code, T data) {
        return BaseResponse.with(code, data);
    }

    protected BaseResponse response(Code code) {
        return BaseResponse.with(code);
    }

    protected <T> BaseResponse<BasePageableResponse<T>> response(Page<T> page) {
        return BaseResponse.with(Code.SUCCESS, new BasePageableResponse<>(page));
    }

    protected <T> BaseResponse<List<T>> response(List<T> items) {
        return BaseResponse.with(Code.SUCCESS, items);
    }
}
