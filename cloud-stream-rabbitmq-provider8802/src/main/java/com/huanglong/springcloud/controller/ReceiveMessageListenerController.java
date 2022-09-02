package com.huanglong.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @EnableBinding注解底层有Conpoment注解
 */

@EnableBinding(Sink.class) //接收消息
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    /**
     * 1.发送信息的类型是什么，那么接收的类型也是什么
     * 这里发送的是String类型 ，接收也为String  Message<String>
     *  2.发送使用的是withPayload 那么接收就要使用getPayload
     */
    @StreamListener(Sink.INPUT)/**  Stream监听  input输入==接收 */
    public void input(Message<String> message)
    {
        //获取有效载荷getPayload
        System.out.println("消费者1号，------->接收到的消息：" + message.getPayload()+"\t port: "+serverPort);
    }
}
