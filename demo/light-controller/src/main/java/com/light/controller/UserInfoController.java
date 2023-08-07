package com.light.controller;

import com.light.model.Response;
import com.light.model.UserInfo;
import com.light.redis.JedisUtil;
import com.light.service.RewriteEqualsService;
import com.light.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author light
 * @Date 2023/2/24 14:37
 * @Desc 类注解：@RestController @RequestMapping
 * 方法注解：
 **/

@Slf4j
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Autowired
    private RewriteEqualsService rewriteEqualsService;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/getName")
    public Response getName(){
        Response response = new Response();
        response.setRetMsg("对象地址" + getUserInfoService());
        return response;
    }

    public UserInfoService getUserInfoService(){
        return applicationContext.getBean(UserInfoService.class);
    }

    @PostMapping("/test")
    public Response test(String userId) {
        Response response = new Response();
        return response;
    }

    @PostMapping("/register")
    public Response register(String name, Integer age) {
        Response response = new Response();
        userInfoService.register(name, age);
        return response;
    }

    @PostMapping("/getUserName")
    public Response register(String userId) {
        log.debug("在系统运行时是不会打印出来的，只有debug才会，用户ID:{}",userId);
        Response response = new Response();
        String name = userInfoService.getUserNameById(userId);
        response.setRetMsg(name);
        return response;
    }


    @PostMapping("/aop")
    public Response aopTest(UserInfo user) throws InterruptedException {
        log.info("输入参数：name:{},age:{}", user.getName(), user.getAge());
        Thread.sleep(1000);
        return new Response();
    }


    private void getThreadPool() {
        // 创建单线程-线程池，任务依次执行
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println(threadPoolExecutor);

        for (int i = 0; i < 10; i++) {
            //创建任务
            Runnable runnable = () -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            };
            // 将任务交给线程池管理
            threadPoolExecutor.execute(runnable);
        }
        //关闭线程池
        threadPoolExecutor.shutdown();
    }

}

