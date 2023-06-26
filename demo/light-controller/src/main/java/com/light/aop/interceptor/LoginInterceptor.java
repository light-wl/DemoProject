package com.light.aop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
/**
 * @Author light
 * @Date 2023/6/26
 * @Desc 拦截器
 **/


public class LoginInterceptor implements HandlerInterceptor {

    /**
     * preHandle: 预先处理,在目标的controller方法执行之前,进行处理
     * postHandle: 在目标的controller方法执行之后,到达指定页面之前进行处理
     * afterCompletion: 在页面渲染之后进行处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object reglister = session.getAttribute("Reglister");

        if (reglister != null) {
            return true;
        } else {
            request.setAttribute("msg", "请先登录!");

            //这里直接跳转到首页了
//            request.getRequestDispatcher("/").forward(request,response);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
