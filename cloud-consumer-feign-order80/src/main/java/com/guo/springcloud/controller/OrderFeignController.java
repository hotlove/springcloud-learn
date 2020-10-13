package com.guo.springcloud.controller;

import com.guo.springcloud.entities.CommonResult;
import com.guo.springcloud.entities.Payment;
import com.guo.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/9 22:03
 * @Description:
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return paymentFeignService.queryById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    String paymentFeignTimeout(){
        // openfeign-ribbon,客户端一般默认等待1s
        return paymentFeignService.paymentFeignTimeout();
    }
}
