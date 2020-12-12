package com.guo.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/12/7 22:26
 * @Description:
 */
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "------testB";
    }

    @GetMapping("/sleep")
    public String testSleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "sleep....";
    }
}
