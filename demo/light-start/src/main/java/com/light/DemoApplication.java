package com.light;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;

//1-@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) 因为导入了mybatis的jar包，如果不提供数据源，会报错，所以需要暂时先剔除这个参数
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DemoApplication implements Serializable {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
