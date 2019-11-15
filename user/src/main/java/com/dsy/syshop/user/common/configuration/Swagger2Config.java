package com.dsy.syshop.user.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 15:04
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createAdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .apiInfo(adminInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dsy.syshop.user"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo adminInfo() {
        return new ApiInfoBuilder()
                .title("siyushop User 接口")
                .description("siyushop User 接口")
                .version("1.0")
                .build();
    }
}
