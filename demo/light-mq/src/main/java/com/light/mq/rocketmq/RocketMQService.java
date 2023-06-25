package com.light.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author light
 * @Date 2023/6/18
 * @Desc
 **/
@Slf4j
@Service
public class RocketMQService {
    /**
     * 引入template代替defaultMQProducer作为生产者
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.topic}")
    private String topic;

    public String sendMsg(String msg) {
        //通过MessageBuilder构建消息
        Message<String> message = MessageBuilder.withPayload(msg).build();
        //同步发送该消息，获取发送结果
        SendResult result = rocketMQTemplate.syncSend(topic, message);
        return result.getMsgId() + "----" + result.getSendStatus();
    }
}
