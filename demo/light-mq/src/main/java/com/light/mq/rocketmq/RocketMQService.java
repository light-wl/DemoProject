package com.light.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    // 实例化消息生产者Producer
    private volatile DefaultMQProducer producer;
    private volatile DefaultMQPushConsumer consumer;

    public String sendMsg(String msg) {
        //使用单例模式，创建producer
        createProducer();
        SendResult sendResult = new SendResult();
        try {
            // 创建消息，并指定Topic，Tag和消息体
            Message msgTopic = new Message(topic, "TagA", (msg).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息到一个Broker
            sendResult = producer.send(msgTopic);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            System.out.println(e);
        }
//        如果直接关闭，会导致console根据这个组查询不到消息
//        producer.shutdown();
        return sendResult.getMsgId() + "----" + sendResult.getSendStatus();
    }

    public String getMsg() {
        createConsumer();
        return "消费成功";
    }

    private void createProducer() {
        if (producer == null) {
            synchronized (DefaultMQProducer.class) {
                if (producer == null) {
                    producer = new DefaultMQProducer("bootProducer");
                    // 设置NameServer的地址
                    producer.setNamesrvAddr("192.168.3.28:9876");
                    // 设置消息同步发送失败时的重试次数，默认为 2
                    producer.setRetryTimesWhenSendFailed(2);
                    // 设置消息发送超时时间，默认3000ms
                    producer.setSendMsgTimeout(3000);

                    // 因为shutdown会让我的console根据这个组查询不到消息，所以暂时只开启一次
                    try {
                        // 启动Producer实例
                        producer.start();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    private void createConsumer() {
        if (consumer == null) {
            synchronized (DefaultMQProducer.class) {
                if (consumer == null) {
                    try {
                        // 实例化消息生产者,指定组名
                        consumer = new DefaultMQPushConsumer("bootConsumer");
                        // 指定Namesrv地址信息.
                        consumer.setNamesrvAddr("localhost:9876");
                        // 订阅Topic
                        consumer.subscribe("bootTopic", "*");
                        //负载均衡模式消费(消费者采用集群方式消费消息，一条消息同一个消费者组中只有一个消费者会消费到)
                        consumer.setMessageModel(MessageModel.CLUSTERING);
                        // 注册回调函数，处理消息
                        consumer.registerMessageListener(new MessageListenerConcurrently() {
                            @Override
                            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                            ConsumeConcurrentlyContext context) {
                                System.out.printf("%s Receive New Messages: %s %n",
                                        Thread.currentThread().getName(), msgs);
                                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                            }
                        });
                        //启动消息者
                        consumer.start();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
}
