package com.light.a.javabase;

/**
 * @Author light
 * @Date 2023/8/7
 * @Desc
 **/
public class SynchronizedDemo {
    public static void main(String[] args) {
        synchronizedTest();
    }
    public static void synchronizedTest(){
        int num = 0;
        for (int i = 0; i < 100; i++) {
            synchronized (SynchronizedDemo.class){
                num++;
            }
        }
        System.out.println(num);
    }
}
