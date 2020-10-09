package com.guo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/9 21:51
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients // 激活并开启feign
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
