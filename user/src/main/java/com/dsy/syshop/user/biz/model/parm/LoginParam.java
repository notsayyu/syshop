package com.dsy.syshop.user.biz.model.parm;

import com.dsy.syshop.core.common.constant.RegexPattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 15:30
 */
@ApiModel(value = "登录参数")
@Data
public class LoginParam {
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "请输入手机号")
    @NotEmpty(message = "请输入手机号")
    @Pattern(regexp = RegexPattern.MOBILE_ALL, message = "请输入正确的手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "请输入密码")
    @NotEmpty(message = "请输入密码")
    @Pattern(regexp = RegexPattern.PASSWORD_RULE, message = "密码有误，长度8-16位必须包含字母、数字、符号中至少2种")
    private String password;

//    @ApiModelProperty(value = "短信验证码")
//    @NotNull(message = "请输入验证码")
//    @NotEmpty(message = "请输入验证码")
//    private String smsCode;

    @ApiModelProperty(value = "是否自动登录")
    private boolean autoLogin;
}
