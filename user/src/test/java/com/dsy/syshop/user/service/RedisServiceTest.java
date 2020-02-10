package com.dsy.syshop.user.service;

import com.dsy.syshop.core.biz.service.RedisService;
import com.dsy.syshop.user.UserApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: dsy
 * @date: 2020/2/10 11:57
 */
@Slf4j
public class RedisServiceTest extends UserApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    public void setKeyTest() {

        redisService.saveSet("notsay", "代思宇");
        System.out.println(redisService.getValue("notsay"));

    }
}
