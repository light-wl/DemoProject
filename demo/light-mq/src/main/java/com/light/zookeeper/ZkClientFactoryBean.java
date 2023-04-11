package com.light.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * @Description 定义zookeeper客户端
 * @Author light
 * @Date 2023/4/11 10:26
 **/
@Component
public class ZkClientFactoryBean implements FactoryBean<CuratorFramework> {
    @Value("${zookeeper.connectString:localhost:2181}")
    private String connectString;

    @Value("${zookeeper.maxRetries:3}")
    private int maxRetries;

    @Value("${zookeeper.baseSleepTimeMs:1000}")
    private int baseSleepTimeMs;

    private CuratorFramework curatorClient;

    @PostConstruct
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        curatorClient = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(retryPolicy)
                .build();
        curatorClient.start();
    }

    @Override
    public CuratorFramework getObject() throws Exception {
        return curatorClient;
    }

    @Override
    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }
}

