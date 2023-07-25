package com.light.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author light
 * @Date 2023/7/21
 * @Desc
 **/
@Controller
@RequestMapping(value = "/kafka")
public class KafkaMQController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping(value = "/producer")
    public void consume() {
        for (int i = 0; i < 3; i++) {
            ListenableFuture future = kafkaTemplate.send("kafkaTopic", "hello");
            future.addCallback(result -> {
                System.out.println("发送消息成功");
            }, (KafkaFailureCallback<Integer, String>) ex -> {
                System.out.println("发送消息失败");
            });
        }
    }

    /**
     * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
     */
    @KafkaListener(topics = "kafkaTopic")
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("process:" + record.topic() + record.partition() + record);
    }

}


