package com.light.project;

import com.light.utils.DateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author light
 * @Date 2023/6/7
 * @Desc 延时任务的几种实现方式
 * 1、Redis
 * 2、MQ
 * 3、JDK:使用DelayQueue实现订单自动取消
 * 优点：使用 DelayQueue, 只需要有一个线程不断从队列中获取数据即可，它的优点是不用引入第三方依赖，实现也很简单，
 * 缺点：它是内存存储，对分布式支持不友好，如果发生单点故障，可能会造成数据丢失，无界队列还存在 OOM 的风险。
 * DelayQueue是一个有序的无界BlockingQueue，用于放置实现了Delayed接口的对象，其中的对象在到期时才能从队列中取走。
 * DelayQueue只能添加实现了Delayed接口的对象，不能将null元素放置到这种队列中。
 **/
@Slf4j
public class DelayQueueProject {

    // 定义一个延迟队列
    public volatile static DelayQueue<Orders> orderQueue = new DelayQueue();

//    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        Orders orders = Orders.builder()
                .orderNumber("1")
                .time(DateUtil.addInteger(new Date(), Calendar.SECOND, 10))
                .build();
        DelayQueueProject.orderQueue.add(orders);
        log.info("订单" + orders.getOrderNumber() + "未付款，到期时间 = {}", orders.getTime());

        OrderDelayQueueJob queueJob = new OrderDelayQueueJob();
        queueJob.run();
    }


    /**
     * DelayQueue是一个有序的无界BlockingQueue，用于放置实现了Delayed接口的对象，其中的对象在到期时才能从队列中取走。
     * DelayQueue只能添加实现了Delayed接口的对象，不能将null元素放置到这种队列中。
     */
}

/**
 * 订单实体类
 */
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
class Orders implements Delayed {

    private String orderNumber;

    private Date time;

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return time.compareTo(((Orders) o).getTime());
    }

}


/**
 * 延迟队列自动取消订单
 */
@Slf4j
//@Component
class OrderDelayQueueJob implements CommandLineRunner {

    public void orderTask() {
        log.info("开启自动取消订单job,当前时间 = {}", DateUtil.date2Str(new Date(), DateUtil.TO_SECOND_LONG));

        while (true) {
            try {
                // 获取指定订单信息
                Orders order = DelayQueueProject.orderQueue.take();
                // 从队列中删除该数据
                DelayQueueProject.orderQueue.remove(order);
                log.info("订单" + order.getOrderNumber() + "超时取消，取消时间 = {}",
                        DateUtil.date2Str(new Date(), DateUtil.TO_SECOND_LONG));
                log.info("Initial Size = {}", DelayQueueProject.orderQueue.size());
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void run(String... args) {
        // 自动取消订单开启
        Thread thread = new Thread(this::orderTask);
        thread.start();
    }
}

