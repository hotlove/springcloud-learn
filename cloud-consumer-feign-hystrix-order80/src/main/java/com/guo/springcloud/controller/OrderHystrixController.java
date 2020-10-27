package com.guo.springcloud.controller;

import com.guo.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "defaultFallBack")
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

    @GetMapping("/consumer/hystrix/self/timeout/{id}")
    // 客户端对自己得保护
    // 这句话可以理解为客户端只等待1.5s 要是1.5s还没有结果返回则就执行兜底方案也就是下面得降级方案
    // 或者客户端报错也会进行降级
    // 这里因为指定了详细的属性，所以这里不会使用到默认的服务降级方法
    @HystrixCommand(fallbackMethod = "paymentInfoHystrixTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="1500")
    })
    String paymentInfoHystrixTimeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoTimeout(id);
    }

    String paymentInfoHystrixTimeoutHandler(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙请10s后再试或者自己运行出错请检查自己";
    }

    @GetMapping("/consumer/hystrix/default/timeout/{id}")
    // 这里配置了hystrixcommand标签表示启动服务降级，但是没有配置具体得属性值，这里就会使用默认的全局服务降级方法
    @HystrixCommand
    String paymentInfoHystrixTimeoutDefault(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoTimeout(id);
    }

    // 下面是全局fallback
    public String defaultFallBack() {
        return "这是全局服务得降级";
    }
}
