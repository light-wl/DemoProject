package com.light.async;

import com.light.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author light
 * @Date 2023/5/8
 * @Desc 实现异步的八种方式：
 * 1-线程Thread
 * 2-Future
 * 3-异步框架CompletableFuture
 * 4-Spring注解@Async
 * 5-Spring ApplicationEvent事件
 * 6-消息队列
 * 7-第三方异步框架，比如Hutool的ThreadUtil
 * 8-Guava异步
 **/
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    /**
     * 无返回值的异步任务
     * */
    @GetMapping("/asyncServer")
    public Response asyncServer() {
        Response response = new Response();
        Long start = System.currentTimeMillis();
        asyncService.addScore();
        asyncService.sendMsg();
        Long end = System.currentTimeMillis();
        response.setRetMsg("同步耗时：" + (end - start));
        return response;
    }
}
