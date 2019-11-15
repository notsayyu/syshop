package com.dsy.syshop.user.web.controller;

import com.dsy.syshop.user.common.configuration.security.UserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public String getHello(@ApiIgnore @AuthenticationPrincipal UserDetail manager) {
        int id = manager.getId();
        System.out.println(id);
        return String.valueOf(id);
    }
}
