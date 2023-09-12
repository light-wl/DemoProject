package com.light.a.javabase;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Method;

/**
 * @Author light
 * @Date 2023/2/24 15:02
 * @Desc 反射的三种实现方式
 * 第一种方式：通过实例化对象的 getClass() 方法实现反射
 * 第二种方式：通过类路径实现反射，Class.forName()程序中用的最多
 * 第三种方式：通过类名实现反射
 * <p>
 * 优点：java的反射机制就是增加程序的灵活性，避免将程序写死到代码里，
 * 实例：类加载器加载类是通过方法区上类的信息，在堆上创建一个类的Class对象，这个class对象会作为运行时创建该类对象的模版。
 * 这个class对象是唯一对应该类的，要区分所谓的实例和class对象。
 **/
//@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Reflect {

    //    @Scheduled(cron = "0/5 * * * * ?")
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
        Reflect entity = new Reflect();
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
            Class entityClass = Class.forName("com.light.a.javabase.Reflect");
            String entityName = entityClass.getName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 第三种方式：通过类名实现反射
     */
    public static void ReflectThree() {
        Class entityClass = Reflect.class;
        String entityName = entityClass.getName();
    }

    public void ReflectImpl() {
        try {
            /** 一个完整的示例 */
            // 获取Class对象
            Class<?> clazz = Class.forName("com.light.a.javabase.ReflectSon");
            //获取继承的父类
            Class<?> superClass = clazz.getSuperclass();
            // 获取Method对象：getDeclaredMethod 可以调用类中的所有方法（不包括父类中继承的方法）
            Method method = clazz.getDeclaredMethod("selfPublic", String.class, int.class);
            // 获取Method对象2：getMethod可以调用类中有访问权限的方法（包括父类中继承的方法）
            // Method method=clazz.getMethod(name);
            Object obj = clazz.newInstance();
            // 如果是私有方法，需要单独设置方法可访问性
            method.setAccessible(true);
            // 调用方法
            method.invoke(obj, "str", 10);

            System.out.println(clazz.getPackage().getName()); //包名
            System.out.println(clazz.getSimpleName()); //类名
            System.out.println(clazz.getName()); //完整类名

        } catch (Exception e) {

        }
    }
}
