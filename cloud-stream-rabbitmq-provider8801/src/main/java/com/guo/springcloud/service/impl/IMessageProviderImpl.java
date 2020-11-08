package com.guo.springcloud.service.impl;

import com.guo.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/11/8 17:51
 * @Description:
 */
// 定义消息的推送管道 这个是源
@EnableBinding(Source.class) // 这个注解可以理解为信道channel和exchage绑定
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();

        boolean send = output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*************** serial:" + serial);
        return serial;
    }
}
