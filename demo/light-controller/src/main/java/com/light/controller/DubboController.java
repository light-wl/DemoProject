package com.light.controller;

import com.light.consumer.DubboConsumerService;
import com.light.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author light
 * @Date 2023/6/19
 * @Desc
 **/
@Slf4j
@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Autowired
    private DubboConsumerService consumerService;

    @GetMapping("/getMsg")
    public Response sendMsg() {
        Response response = new Response();
        String msg = consumerService.getMsg();
        response.setRetMsg("发送消息成功:" + msg);
        return response;
    }
}
