package com.dsy.syshop.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {
        "com.dsy.syshop.core",
        "com.dsy.syshop.user"
})
@EnableJpaRepositories(basePackages = {
        "com.dsy.syshop.core",
        "com.dsy.syshop.user"
})
@EntityScan(basePackages = {
        "com.dsy.syshop.core",
        "com.dsy.syshop.user"
})
@EnableCaching
@EnableAsync
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
