package com.guo.springcloud.controller;

import com.guo.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/26 21:56
 * @Description:
 */
@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    String paymentInfoOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoOk(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfoTimeout(id);
    }
}
