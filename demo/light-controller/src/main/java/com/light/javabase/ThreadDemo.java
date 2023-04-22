package com.light.javabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author light
 * @Date 2023/2/28 11:09
 **/
public class ThreadDemo {

    static int count = 0;

    public void TestOne() {
        long startTime = System.currentTimeMillis();
        synThread();
        long endTime = System.currentTimeMillis();
        System.out.println("同步一共耗时：" + (endTime - startTime) + "毫秒");
    }

    /**
     * 100个线程同时对一个变量进行+-操作，看看结果如何
     */
    private static void threadTest() {
        List<Thread> threadList = new ArrayList<>(1000);
        for (int i = 0; i < 50; i++) {
            threadList.add(getThreadAdd());
            threadList.add(getThreadMinus());
        }
        for (int i = 0; i < 100; i++) {
            threadList.get(i).start();
        }
        System.out.println(count);
    }

    /**
     * 同步和异步操作线程，看看时间差是多少
     */
    private static void synThread() {
        Thread thread = getThreadAdd();
        for (int i = 0; i < 10; i++) {
            thread.start();
        }
    }

    private static void asynThread() {

    }

    private static Thread getThreadAdd() {
        return new Thread(() -> {
            count++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Thread getThreadMinus() {
        return new Thread(() -> {
            count--;
        });
    }
}
