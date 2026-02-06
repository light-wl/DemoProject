package com.alibaba.dubbo.demo.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by wy on 2017/4/13.
 */
public class Provider {
        public static void main(String[] args) throws IOException {
//            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
//            System.out.println(context.getDisplayName() + ": here");
//            context.start();
//            System.out.println("服务已经启动...");
//            System.in.read();
            String timeStr = "2025-08-26T10:14:42.338878Z";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTimeUtil.parse(timeStr, formatter);
            System.out.println("time: " + time);
        }
    }

