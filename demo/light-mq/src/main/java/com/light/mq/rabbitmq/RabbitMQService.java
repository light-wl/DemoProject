package com.light.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author light
 * @Date 2023/6/17
 * @Desc RabbitMQ
 **/
@Slf4j
@Service
public class RabbitMQService {

    @Resource
    private RabbitAdmin rabbitAdmin;

    /**
     * 创建三种交换机，三个队列
     */
    public void initExchangeAndQueue() {
        /*
         * 三种交换机，
         * 扇形交换机：扇型交换机，这个交换机没有路由键概念，就算你绑了路由键也是无视的。 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。
         * 直连交换机：根据路由key投放消息到对应的队列
         * 主题交换机
         * */
        rabbitAdmin.declareExchange(new FanoutExchange("test.fanout.exchange", false, false, null));
        rabbitAdmin.declareExchange(new DirectExchange("test.direct.exchange", false, false, null));
        rabbitAdmin.declareExchange(new TopicExchange("test.topic.exchange", false, false, null));

        rabbitAdmin.declareQueue(new Queue("fanout.queue", false, false, false, null));
        rabbitAdmin.declareQueue(new Queue("direct.queue", false, false, false, null));
        rabbitAdmin.declareQueue(new Queue("topic.queue", false, false, false, null));
    }

    /**
     * 创建绑定关系，将队列绑定到交换机上
     */
    public void initBinding() {

        //1、将一个交换机绑定到另一个交换机(消息流转topic.exchange->fanout.exchange)
        rabbitAdmin.declareBinding(new Binding("test.fanout.exchange",//目标：交换机名
                Binding.DestinationType.EXCHANGE, //绑定目标类型：交换机
                "test.topic.exchange", //发起绑定的交换机
                "test", //路由key
                null));
        //1.1、fanout.queue绑定到test.fanout.exchange
        rabbitAdmin.declareBinding(new Binding("fanout.queue",//目标：fanout.queue
                Binding.DestinationType.QUEUE,//绑定类型:队列
                "test.fanout.exchange",//绑定到的exchange
                "",//因为是fanout类型exchange所以不需要routingKey
                null));

        //2、将一个queue绑定到一个exchange
        rabbitAdmin.declareBinding(new Binding("direct.queue",//目标：队列名
                Binding.DestinationType.QUEUE,//绑定目标类型：队列
                "test.direct.exchange",//交换机名称
                "direct.key",//路由key
                null));//扩展参数


        //3、TOPIC主题交换机
        rabbitAdmin.declareBinding(new Binding("topic.queue",//目标：topic.queue
                Binding.DestinationType.QUEUE,//绑定类型:队列
                "test.topic.exchange",//绑定到的exchange
                "user.*",//（ *只能匹配一个词；#可以匹配多个词 ）
                null));
    }

    /**
     * 简单模式，工作模式：生产者直接发送消息到队列，
     * 发布订阅模式：就是使用扇形交换机
     * Routing路由模式：就是使用直连交换机
     * Topics模式：就是使用主题交换机（ *只能匹配一个词；#可以匹配多个词 ）
     */
    public void sendMsg() {
        /**
         * 简单模式：简单模式下会使用默认的""作为交换机名称
         * 工作模式：就是比简单模式多了一些消费端，提高处理速度，发送模式一样
         * 如果没有指定exchange，那么默认采用AMQP defuault
         * */
        rabbitAdmin.getRabbitTemplate().convertAndSend("", "", "msg");

        /**
         * 其余三种模式的使用方式都一样，只是交换机的种类不同而已
         * */
        rabbitAdmin.getRabbitTemplate().convertAndSend("routingKey", "msg");
        //发送消息
        //正常的消息流转 从test.direct.exchange-》direct.queue
        rabbitAdmin.getRabbitTemplate().convertAndSend("test.direct.exchange", "direct.key", "直连交换机消息111");
        //消息先到test.topic.exchange-》test.fanout.exchange-》fanout.queue
        rabbitAdmin.getRabbitTemplate().convertAndSend("test.topic.exchange", "test", "多级流转消息2222");

    }

    /**
     * 当确认模式设置为NONE时，只要中间件投递了消息就认为成功并将消息从队列中移除。
     */
//    @RabbitListener(queues = "direct.queue", ackMode = "NONE")
    public void noneAckListener(String msg) {
        log.info("收到消息 -> {}", msg);
        // 添加个错误用于测试
        int a = 1 / 0;
    }

    /**
     * 消息确认后，返回信息
     */
    @RabbitListener(queues = "direct.queue")
    public String returnMQMsg(String msg) {
        log.info("收到消息 -> {}", msg);
        return "消息已经收到，返回消息";
    }

}
