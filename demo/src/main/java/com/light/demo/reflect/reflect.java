package com.light.demo.reflect;

import java.lang.reflect.Method;

/**
 * 参考：https://blog.csdn.net/qq_51515673/article/details/124830558
 * */
public class reflect {
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
