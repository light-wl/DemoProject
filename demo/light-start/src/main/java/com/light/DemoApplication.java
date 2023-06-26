package com.light;

import com.light.aop.filter.LogFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.Serializable;

@SpringBootApplication
@MapperScan("com.light.mapper")
@EnableScheduling
@EnableAsync
@EnableRabbit
public class DemoApplication implements Serializable {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * 注册定义好的过滤器：
     * 我们创建了一个名为logFilter的FilterRegistrationBean对象，并设置了要注册的过滤器LogFilter，
     * 过滤的路径addUrlPatterns("/*")，过滤器的名称setName("LogFilter")和执行顺序setOrder(1)。
     * */
    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LogFilter");
        registrationBean.setOrder(2);
        return registrationBean;
    }

}

