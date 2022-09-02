package com.huanglong.springcloud.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.huanglong.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;


/**
 * 消息发送者实现类
 * 这里不再是注解@Service了 ，要开启绑定器，与 Binder进行沟通
 *  @EnableBinding 生产者消息的发送管道
 */
@EnableBinding(Source.class)//定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output;//消息发送管道

    @Override
    public String send() {
        String message = IdUtil.simpleUUID();
        //把消息用绑定器绑定起来，通过管道（output）进行发送（send）  build构建出来
        output.send(MessageBuilder.withPayload(message).build());
        System.out.println("*****************message"+message);
        return null;
    }
}
