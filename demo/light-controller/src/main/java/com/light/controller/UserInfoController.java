package com.light.controller;

import com.light.model.Response;
import com.light.model.User;
import com.light.service.RewriteEqualsService;
import com.light.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:37
 **/
@RestController
@Scope("prototype")
@Slf4j
@Controller
@Service
@Component
public class UserInfoController {
    //    @Resource
    private UserInfoService userInfoService;

    @Autowired
    private RewriteEqualsService rewriteEqualsService;

    @PostMapping("/test")
    public Response test() {
//         userInfoService = SpringUtils.getBean("userInfoService");
//        userInfoService.count++;
//        UserInfoService.countStatic++;
//        System.out.println(this);
//        System.out.println(userInfoService);
//        System.out.println(userInfoService.count + "|" + UserInfoService.countStatic);
        this.getThreadPool();
        return new Response();
    }

    @PostMapping("/aop")
    public Response aopTest(User user) throws InterruptedException {
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
