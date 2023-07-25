package com.light.aop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @Author light
 * @Date 2023/6/26
 * @Desc 拦截器需要配置拦截路径
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * addPathPatterns("/**"):拦截所有的资源(我已经将拦截器的验证逻辑注释掉了，所以这里会拦截也没关系)
         * excludePathPatterns：将首页和静态资源方向
         */
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/static/**");
    }

}
