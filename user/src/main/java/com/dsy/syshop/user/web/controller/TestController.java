package com.dsy.syshop.user.web.controller;

import com.dsy.syshop.core.common.enums.Code;
import com.dsy.syshop.core.web.response.BaseResponse;
import com.dsy.syshop.user.common.configuration.security.UserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/8 10:32
 */
@RestController
@RequestMapping("/user/test")
public class TestController {

    @GetMapping
    public BaseResponse getHello(@ApiIgnore @AuthenticationPrincipal UserDetail manager) {
        int id = manager.getId();
        System.out.println(id);
        return BaseResponse.with(Code.SUCCESS, manager);
    }

    @DeleteMapping(value = "/delete")
    public BaseResponse delete(@RequestParam Integer id, @RequestParam String name) {
        return BaseResponse.with(Code.SUCCESS);
    }
}
