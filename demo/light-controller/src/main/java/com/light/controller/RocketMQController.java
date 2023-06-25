package com.light.controller;

import com.light.model.Response;
import com.light.mq.rabbitmq.RabbitMQService;
import com.light.mq.rocketmq.RocketMQService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author light
 * @Date 2023/6/18
 * @Desc
 **/
@Slf4j
@RestController
@RequestMapping("/rocketmq")
public class RocketMQController {
    @Autowired
    private RocketMQService rocketMQService;

    @GetMapping("/sendMsg")
    public Response sendMsg() {
        Response response = new Response();
        String msg = "rocketMQ 的 消息";
        String result = rocketMQService.sendMsg(msg);
        response.setRetMsg("发送消息成功，result" + result);
        return response;
    }
}
