package com.guo.springcloud.controller;

import com.guo.springcloud.entities.CommonResult;
import com.guo.springcloud.entities.Payment;
import com.guo.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date: 2020/8/26 19:55
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        int i = paymentService.create(payment);
        if (i > 0) {
            return new CommonResult(1, "添加成功");
        } else {
            return new CommonResult(0, "添加失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> queryById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        if (paymentById != null) {
            log.info("hahahhaha");
            return new CommonResult<Payment>(1, "查询成功", paymentById);
        } else {
            return new CommonResult<>(0, "查寻失败");
        }
    }
}
