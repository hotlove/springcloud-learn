package com.guo.springcloud.controller;

import com.guo.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/11/8 17:58
 * @Description:
 */
@RestController
public class SendMessgeController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
