package com.light.service;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 15:02
 **/
public class ReflectService {
        public static void main(String[] args) {
        try {
            Class clazz = Class.forName("com.light.demo.reflect.User");
/*            System.out.println(clazz.getPackage().getName()); //包名
            System.out.println(clazz.getSimpleName()); //类名
            System.out.println(clazz.getName()); //完整类名*/

            //获取公共方法
            Method methodPub = clazz.getDeclaredMethod("getName");
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
