package com.guo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/11/6 20:58
 * @Description:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCeneter3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCeneter3344.class, args);
    }
}
