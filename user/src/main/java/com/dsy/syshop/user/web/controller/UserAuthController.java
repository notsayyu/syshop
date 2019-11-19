package com.dsy.syshop.user.web.controller;

import com.dsy.syshop.core.web.response.BaseResponse;
import com.dsy.syshop.user.biz.model.parm.LoginParam;
import com.dsy.syshop.user.biz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 14:44
 */
@Api(value = "用户鉴权", tags = "用户鉴权")
@RestController
@RequestMapping(value = "/user/auth")
public class UserAuthController extends BaseController {
    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登陆接口")
    public BaseResponse login(@ApiParam(value = "登陆参数") @RequestBody @Validated LoginParam param) {
        return response(userService.login(param));
    }

}
