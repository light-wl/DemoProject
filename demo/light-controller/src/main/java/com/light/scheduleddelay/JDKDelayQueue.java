package com.light.scheduleddelay;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author light
 * @Date 2023/7/3
 * @Desc JDK 自带的延时队列实现延时任务
 * 该方案是利用JDK自带的DelayQueue来实现，这是一个无界阻塞队列，该队列只有在延迟期满的时候才能从中获取元素，放入DelayQueue中的对象，是必须实现Delayed接口的。
 * Poll():获取并移除队列的超时元素，没有则返回空
 * take():获取并移除队列的超时元素，如果没有则wait当前线程，直到有元素满足超时条件，返回结果。
 **/
@Component
@NoArgsConstructor
public class JDKDelayQueue implements Delayed {
    private String orderId;
    private long timeout;

    JDKDelayQueue(String orderId, long timeout) {
        this.orderId = orderId;
        this.timeout = timeout + System.nanoTime();
    }

    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        JDKDelayQueue t = (JDKDelayQueue) other;
        long d = (getDelay(TimeUnit.NANOSECONDS) - t.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    // 返回距离你自定义的超时时间还有多少
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public void print() {
        System.out.println(orderId + "编号的订单要删除啦。。。。");
    }

//    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("00000001");
        list.add("00000002");
        list.add("00000003");
        list.add("00000004");
        list.add("00000005");

        DelayQueue<JDKDelayQueue> queue = new DelayQueue<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            System.out.println("开始：" + i);
            //延迟三秒取出
            queue.put(new JDKDelayQueue(list.get(i), TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS)));
            try {
                queue.take().print();
                System.out.println("After " + (System.currentTimeMillis() - start) + " MilliSeconds");
            } catch (InterruptedException e) {
            }
        }
    }

}
