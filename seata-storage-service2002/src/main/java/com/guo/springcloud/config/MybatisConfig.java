package com.guo.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/26 16:30
 * @Description:
 */
@Configuration
@MapperScan({"com.guo.springcloud.dao"})
public class MybatisConfig {
}
