package com.guo.springcloud.service;

import com.guo.springcloud.entities.CommonResult;
import com.guo.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/9 21:53
 * @Description:
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> queryById(@PathVariable("id") Long id);
}
