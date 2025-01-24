package com.light.scheduleddelay;

//import com.light.redis.JedisPoolConfig;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Tuple;

import java.util.Calendar;
import java.util.Set;

/**
 * @Author light
 * @Date 2023/7/3
 * @Desc
 **/
public class RedisDelay {
//    private Jedis jedis = null;
//
//    //    @Scheduled(cron = "0/5 * * * * *")
//    public void test() {
//        RedisDelay redisDelay = new RedisDelay();
//        redisDelay.init();
//        redisDelay.productionDelayMessage();
//        redisDelay.consumerDelayMessage();
//    }
//
//    public void init() {
//        jedis = JedisPoolConfig.getJedis();
//    }
//
//    //生产者,生成5个订单放进去
//    public void productionDelayMessage() {
//        for (int i = 0; i < 5; i++) {
//            //延迟3秒
//            Calendar cal1 = Calendar.getInstance();
//            cal1.add(Calendar.SECOND, 3);
//            int second3later = (int) (cal1.getTimeInMillis() / 1000);
//            jedis.zadd("OrderId", second3later, "OID0000001" + i);
//            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID000000-" + i);
//        }
//    }
//
//    //消费者，取订单
//    public void consumerDelayMessage() {
//        while (true) {
//            Set<Tuple> items = jedis.zrangeWithScores("OrderId", 0, 1);
//            if (items == null || items.isEmpty()) {
//                System.out.println("当前没有等待的任务");
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                continue;
//            }
//
//            int score = (int) ((Tuple) items.toArray()[0]).getScore();
//            Calendar cal = Calendar.getInstance();
//            int nowSecond = (int) (cal.getTimeInMillis() / 1000);
//            if (nowSecond >= score) {
//                String orderId = ((Tuple) items.toArray()[0]).getElement();
//                // 为了防止高并发情况下，这里出错
//                Long num = jedis.zrem("OrderId", orderId);
//                if (num != null && num > 0) {
//                    System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
//                }
//            }
//        }
//    }
}
