package com.light.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * @Author light
 * @Date 2023/2/24 15:02
 * @Desc 反射的三种实现方式
 * 第一种方式：通过实例化对象的 getClass() 方法实现反射
 * 第二种方式：通过类路径实现反射，Class.forName()程序中用的最多
 * 第三种方式：通过类名实现反射
 *
 * 优点：java的反射机制就是增加程序的灵活性，避免将程序写死到代码里，
 * 实例：类加载器加载类是通过方法区上类的信息，在堆上创建一个类的Class对象，这个class对象会作为运行时创建该类对象的模版。
 * 这个class对象是唯一对应该类的，要区分所谓的实例和class对象。
 **/
//@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReflectService {

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        ReflectOne();
    }

    public static void main(String[] args) {
        ReflectOne();
    }

    /**
     * 第一种方式：通过实例化对象的getClass()方法实现反射
     */
    public static void ReflectOne() {
        ReflectService entity = new ReflectService();
        Class entityClass = entity.getClass();
        System.out.println(entityClass);
        String entityName = entityClass.getName();
        System.out.println(entityName);
    }

    /**
     * 第二种方式：通过类路径实现反射
     */
    public static void ReflectTwo() {
        try {
            Class entityClass = Class.forName("com.light.service.ReflectService");
            String entityName = entityClass.getName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 第三种方式：通过类名实现反射
     */
    public static void ReflectThree() {
        Class entityClass = ReflectService.class;
        String entityName = entityClass.getName();
    }

    public void ReflectImpl() {
        try {
            Class clazz = Class.forName("com.light.model.UserInfo");
            System.out.println(clazz.getPackage().getName()); //包名
            System.out.println(clazz.getSimpleName()); //类名
            System.out.println(clazz.getName()); //完整类名

            //获取公共方法
            Method methodPub = clazz.getDeclaredMethod("getUserNameById");
            Method methodPubAndParam = clazz.getDeclaredMethod("getNameAndParam", String.class);
            System.out.println(methodPub.invoke(clazz.newInstance()));
            System.out.println(methodPubAndParam.invoke(clazz.newInstance(), "s"));

            // 获取私有方法
            Method methodPri = clazz.getDeclaredMethod("getAge");
            methodPri.setAccessible(true); // 私有方法需要这么设置，否则无法调用
            System.out.println(methodPri.invoke(clazz.newInstance()));
        } catch (Exception e) {

        }
    }
}
