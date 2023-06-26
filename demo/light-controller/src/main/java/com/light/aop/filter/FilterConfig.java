package com.light.aop.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Author light
 * @Date 2023/6/26
 * @Desc 将过滤器加入到 FilterRegistrationBean 中，可以直接在 启动类中添加，或者单独新建一个 参数类
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<ResponseTimeFilter> responseTimeFilter() {
        FilterRegistrationBean<ResponseTimeFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ResponseTimeFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("ResponseTimeFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
