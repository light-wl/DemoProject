package com.light;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.Serializable;

@SpringBootApplication()
@MapperScan("com.light.mapper")
@EnableScheduling
@EnableAsync
@EnableRabbit
public class DemoApplication implements Serializable {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

