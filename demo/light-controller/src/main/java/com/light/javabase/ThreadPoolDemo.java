package com.light.javabase;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author light
 * @Date 2023/9/9
 * @Desc
 **/
public class ThreadPoolDemo {
        private void getThreadPool() {
        // 创建单线程-线程池，任务依次执行
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println(threadPoolExecutor);

        for (int i = 0; i < 10; i++) {
            //创建任务
            Runnable runnable = () -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            };
            // 将任务交给线程池管理
            threadPoolExecutor.execute(runnable);
        }
        //关闭线程池
        threadPoolExecutor.shutdown();
    }

}
