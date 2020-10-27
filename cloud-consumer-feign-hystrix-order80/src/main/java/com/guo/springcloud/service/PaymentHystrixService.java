package com.guo.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/26 21:52
 * @Description:
 */
@Component
// 这里feign指定fallback类，比如当对方服务器宕机的时候，这个时候就会调用fallback指定的类
// 这里主要就是处理对方宕机一直访问不了，就直接使用服务降级的方法
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Integer id);
}
