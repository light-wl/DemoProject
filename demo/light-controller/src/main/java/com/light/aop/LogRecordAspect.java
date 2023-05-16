package com.light.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author light
 * @Date 2023/5/8
 * @Desc
 **/
@Aspect
@Component
public class LogRecordAspect {
    Logger log = LoggerFactory.getLogger(LogRecordAspect.class);

    /**
     * 配置切入点：注释中引号的部分为自己创建的注解的路径，可以通过该注解请求到切入点中去。
     */
    @Pointcut("@annotation(com.light.aop.LogRecord)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    @Before("logPointcut()")
    public void before(JoinPoint joinPoint) {
        //getSignature());是获取到这样的信息 :修饰符+ 包名+组件名(类名) +方法名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //getAnnotation:方法如果存在这样的注释，则返回指定类型的元素的注释，否则为null
        LogRecord logRecord = method.getAnnotation(LogRecord.class);
        log.info("开始：" + logRecord.content());
    }

    @After("logPointcut()")
    public void after(JoinPoint joinPoint) {
        //getSignature());是获取到这样的信息 :修饰符+ 包名+组件名(类名) +方法名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //getAnnotation:方法如果存在这样的注释，则返回指定类型的元素的注释，否则为null
        LogRecord logRecord = method.getAnnotation(LogRecord.class);
        log.info("结束：" + logRecord.content());
    }
}
