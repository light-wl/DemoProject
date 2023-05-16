package com.light;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;

@SpringBootApplication()
@MapperScan("com.light.mapper")
public class DemoApplication implements Serializable {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
