package com.guo.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/14 21:37
 * @Description:
 */
@Service
public class PaymentService {

    /**
     * 正常访问肯定ok得方法·
     * @param id
     * @return
     */
    public String paymentInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoOk,id:" + id + "\t" + "O(∩_∩)O";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="3000")
    })
    public String paymentInfoTimeOut(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoTimeOut,id:" + id + "\t" + "O(∩_∩)O"+"耗时"+timeNumber+"s";
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoTimeOut,id:" + id + "\t" + "/(ㄒoㄒ)/~~";
    }
}
