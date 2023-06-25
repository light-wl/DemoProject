package com.light.consumer;

import com.light.interfaces.ProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Author light
 * @Date 2023/6/19
 * @Desc
 **/
@Service
public class DubboConsumerService {
    public String getMsg(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        ProviderService providerService = (ProviderService) context.getBean("providerService");
        String str = providerService.showMsg("hello");
        return str;
    }
}
