package com.huanglong.springcloud.service;

/**
 *  消息的发送者  也就是生产者
 */
public interface IMessageProvider {
    String send();//send->发送
}
