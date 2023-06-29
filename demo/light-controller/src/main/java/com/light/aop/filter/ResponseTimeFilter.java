package com.light.aop.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author light
 * @Date 2023/6/26
 * @Desc 过滤器 实现二
 * 实例：用于记录请求的响应时间
 * 1、我们继承了OncePerRequestFilter类，并实现了doFilterInternal方法，在方法中记录了请求的响应时间；
 * 2、要在Spring Boot中注册这个过滤器，可以在@Configuration类中使用FilterRegistrationBean对象；
 **/
public class ResponseTimeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        // 需要注意：doFilterInternal方法中必须调用filterChain.doFilter方法来继续执行后续的过滤器或请求处理程序。
        filterChain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        System.out.println("第二个拦截器打印，请求 " + request.getRequestURI() + " 的响应时间为 " + (endTime - startTime) + " 毫秒");
    }

    /**
     * 另一个功能：验证权限
     */
    private void authHeader(HttpServletRequest request, HttpServletResponse response,
                            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring(7);
            // TODO: 验证token，通过则放行，否则返回错误信息
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
        }
    }
}
