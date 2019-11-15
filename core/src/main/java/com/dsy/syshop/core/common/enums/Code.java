package com.dsy.syshop.core.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/7 16:59
 */
@Getter
@AllArgsConstructor
public enum Code {
    /************************************************
     * 基本状态 0 / 100000 - 100099
     ************************************************/
    SUCCESS(0, "success"),

    UNKNOWN_EXCEPTION(100000, "未知异常"),

    PERMISSION_DENIED(100001, "权限拒绝"),

    PARAMETER_ERROR(100002, "参数校验异常"),

    /************************************************
     * 数据库相关状态 100100 - 100199
     ************************************************/
    CONSTRAIN_VIOLATION(100100, "违反唯一性约束"),

    /************************************************
     * 账户相关状态 100200 - 100299
     ************************************************/
    ACCOUNT_NOT_EXIST(100200, "用户不存在"),

    ACCOUNT_DISABLE(100201, "账号不可用"),

    BAD_CREDENTIALS(100202, "用户名或密码错误"),

    ;
    private int code;
    private String message;
}
