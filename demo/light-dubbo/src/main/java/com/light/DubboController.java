package com.light;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author light
 * @Date 2023/7/14
 * @Desc
 **/
@Component
public class DubboController {

//    @DubboReference
//    private ProviderService providerService;
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void exec(){
//        String name = "TOM";
//        String result = providerService.SayHello(name);
//        System.out.println(result);
//    }
}
