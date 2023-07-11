package com.light.javabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author light
 * @Date 2023/2/28 11:09
 * @Desc 创建线程的四种方式
 * 0、匿名内部类
 * 1、继承Thread类创建线程
 * 2、实现Runnable接口创建线程
 * 3、使用Callable和Future创建线程
 * 4、使用线程池例如用Executor框架
 **/

class ThreadCla extends Thread {
    private static int num = 0;

    @Override
    public void run() {
        num++;
    }

    public static int getNum() {
        return num;
    }
}

class ThreadInf implements Runnable {
    private static int num = 0;

    @Override
    public void run() {
        num++;
    }

    public int getNum() {
        return num;
    }
}

class ThreadCallable implements Callable {
    private static int num = 0;

    // 重写call方法，将此线程需要执行的操作申明在call方法中
    @Override
    public Object call() {
        num++;
        return num;
    }

    public int getNum() {
        return num;
    }
}

public class ThreadDemo {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.testFour();
    }

    /**
     * 使用较多的方式：匿名内部类
     * */
    public void testAnony(){
        new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("匿名内部类方式创建线程");
                }
            }).start();
    }

    /**
     * 第一种方式:继承Thread类创建线程，重写run方法
     */
    public void testOne() {
        List<ThreadCla> threadInfoList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threadInfoList.add(new ThreadCla());
        }
        for (int i = 0; i < 100; i++) {
            threadInfoList.get(i).start();
        }
        System.out.println(ThreadCla.getNum());
    }

    /**
     * 第二种方式:实现Runnable接口创建线程，创建线程时将实现的runnable塞进去
     */
    public void testTwo() {
        List<ThreadInf> threadInfoList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threadInfoList.add(new ThreadInf());
        }
        for (int i = 0; i < 100; i++) {
            new Thread(threadInfoList.get(i)).start();
        }
        System.out.println(threadInfoList.get(0).getNum());
    }

    /**
     * 第三种方式:用 callable 实现与 Runnable 有什么区别？
     * A：相比run()方法，call()可以有返回值
     * A：Call() 方法可以抛出异常
     * A：Call() 支持泛型的返回值
     */
    public void testThree() {
        // 1.创建Callable接口实现类的对象
        // 2.将这个对象传到 FutureTask 构造器中，创建 FutureTask 的对象
        // 3.将FutureTask的对象传递到Thread构造器中，创建Thread的对象，并调用start方法开始线程
        FutureTask futureTask = null;
        for (int i = 0; i < 100; i++) {
            ThreadCallable myCallable = new ThreadCallable();
            futureTask = new FutureTask(myCallable);
            new Thread(futureTask).start();
        }

        try {
            // 4.如果Call方法有返回值，则可以有这一步
            // get()方法的返回值即为FutureTask构造起的参数Callable实现类重写的call()的返回值
            Integer sum = (Integer) futureTask.get();
            System.out.println(Thread.currentThread().getName() + ":" + "sum=" + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 第四种方式：使用 Executor 线程池
     * 注意：如果不主动关闭线程池，则不会结束
     * */
    public void testFour(){
        //创建等待队列
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
        //创建线程池，池中保存的线程数为3，允许的最大线程数为5
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50,TimeUnit.MILLISECONDS,bqueue);
        for (int i = 0; i < 10; i++) {
            pool.execute(new ThreadCla());
        }
        System.out.println(ThreadCla.getNum());
        pool.shutdown();
    }






















}
