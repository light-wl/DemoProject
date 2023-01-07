package com.light.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoApplication.class);
        applicationContext.getBean(ServiceOne.class).getService();
        applicationContext.getBean(ServiceOne.class).getService();
        applicationContext.getBean(ServiceOne.class).getService();
    }
}

