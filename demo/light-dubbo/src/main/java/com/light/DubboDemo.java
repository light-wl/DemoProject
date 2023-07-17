package com.light;

import org.apache.dubbo.common.constants.ClusterRules;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.ProviderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Random;

/**
 * @Author ${USER}
 * @Date ${DATE}
 * @Desc
 **/
@Component
public class DubboDemo {

    /**
     * cluster = ClusterRules.FAIL_SAFE：设置容错策略为快速失败，调用失败直接忽略
     * url = "dubbo://192.168.3.28:20880"：点点直连，当zookeeper不能用或者不能间接调用时，可以直接用提供方的dubbo地址，直接调用
     * */
    @DubboReference(cluster = ClusterRules.FAIL_SAFE)
    private ProviderService providerService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec(){
        String resutl = providerService.SayHello("TOM");
        System.out.println(resutl);
    }
}