package com.light.mq;

import com.light.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author light
 * @Date 2023/6/18
 * @Desc
 **/
@Slf4j
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping("/sendMsg")
    public Response sendMsg() {
        Response response = new Response();
        rabbitMQService.sendMsg();
        response.setRetMsg("发送消息成功");
        return response;
    }
}
