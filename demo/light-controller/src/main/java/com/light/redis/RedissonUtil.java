package com.light.redis;

//import org.redisson.Redisson;
//import org.redisson.api.*;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author light
 * @Date 2023/6/3
 * @Desc redis 客户端 redisson 使用
 * 1、分布式锁
 * 2、五种数据结构的使用
 **/
@Component
public class RedissonUtil {
//    private final String redisAddress = "redis://localhost:6379";
//
//    private static RedissonClient redissonClient;
//
//    /**
//     * 可以使用 @PostConstruct 注解，自己初始化，
//     * 当然，用了Spring，可以在配置文件中配置即可。
//     */
//    @PostConstruct
//    public void init() {
//        //1 创建redission的config对象并配置redis服务器(此处使用singleServer)
//        Config config = new Config();
//        config.useSingleServer().setAddress(redisAddress);
//
//        //2 创建redis客户端redissionClient
//        redissonClient = Redisson.create(config);
//    }
//
//    public static RedissonClient getRedissonClient(){
//        return redissonClient;
//    }
//    /**
//     * 原理：reddission底层也是通过setnx,setex这两个命令结合lua脚本完成redis分布式锁的，通过他的api中的lock和unlock即可完成分布式锁。
//     * （redisson所有指令都通过lua脚本执行）
//     * 看门狗：redisson 自己实现了看门狗机制，当过期时间到了，但是任务没有执行完成，会自动续期，不需要其他操作
//     * Q：如果自己设置超时时间？
//     * A：如果自己设置了过期时间，则默认放弃使用看门狗策略，时间到后则会自动释放锁；
//     */
////    @Scheduled(cron = "0/3 * * * * ?")
//    public void exec() {
//        Thread thread = new Thread(this::distributedLock);
//        System.out.println(thread.getId() + "线程开始启动, 开始时间：" + System.currentTimeMillis());
//        thread.start();
//    }
//
//    /**
//     * 分布式锁
//     */
//    public void distributedLock() {
//        long startTime = System.currentTimeMillis();
//        String LOCK_NAME = "锁的名称";
//        RLock lock = redissonClient.getLock(LOCK_NAME);
//        boolean result = false;
//        try {
//            // 获取锁的最大等待时间；锁自动释放的时间；时间单位；
//            result = lock.tryLock(5, 10, TimeUnit.SECONDS);
//            if (result) {
//                System.out.println(Thread.currentThread().getId() + "线程获取锁成功，开始休息100秒");
//                Thread.sleep(100 * 1000);
//                System.out.println(Thread.currentThread().getId() + "线程休息100秒结束");
//            } else {
//                System.out.println(Thread.currentThread().getId() + "线程获取锁失败");
//            }
//        } catch (Exception e) {
//            System.out.println("执行异常：" + e);
//        } finally {
//            if (result) {
//                lock.unlock();
//                System.out.println("释放锁成功" +
//                        (System.currentTimeMillis() - startTime));
//            }
//        }
//    }
//
//    public void redissonMain() {
//        // 使用五种数据结构
//        useString(redissonClient);
//
//        useHash(redissonClient);
//
//        useList(redissonClient);
//
//        useSet(redissonClient);
//
//        useSortedSet(redissonClient);
//
//    }
//
//    /**
//     * String 数据类型
//     *
//     * @param redissonClient
//     */
//    private static void useString(RedissonClient redissonClient) {
//        RBucket<Object> bucket = redissonClient.getBucket("myString-key");
//        bucket.set("{\"userName\":\"test\",\"userPwd\":\"test\",\"email\":\"xxx@163.com\",\"captcha\":\"3552\"}");
//
//        Object o = bucket.get();
//        System.out.println(o);
//    }
//
//    /**
//     * Hash类型
//     *
//     * @param redissonClient
//     */
//    private static void useHash(RedissonClient redissonClient) {
//
//        RMap<Object, Object> map = redissonClient.getMap("myFirstMap");
//        map.put("productId2", "100055301");
//
//        Object productId2 = map.get("productId2");
//        System.out.println(productId2);
//    }
//
//    /**
//     * List数据类型
//     *
//     * @param redissonClient
//     */
//    private static void useList(RedissonClient redissonClient) {
//        List<String> list = redissonClient.getList("listKey-32");
//        list.add("listValue1");
//        list.add("listValue2");
//
//        String s = list.get(0);
//        System.out.println(s);
//        System.out.println(list);
//    }
//
//    /**
//     * Set数据类型
//     *
//     * @param redissonClient
//     */
//    private static void useSet(RedissonClient redissonClient) {
//        RSet<String> set = redissonClient.getSet("setKey-32");
//        set.add("setValue");
//
//        Iterator<String> iterator = set.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            System.out.println(next);
//        }
//    }
//
//    /**
//     * Zset数据类型
//     *
//     * @param redissonClient
//     */
//    private static void useSortedSet(RedissonClient redissonClient) {
//        RScoredSortedSet<String> sortedSet
//                = redissonClient.getScoredSortedSet("sortedKey-32");
//
//        sortedSet.add(1.0, "zs");
//        sortedSet.add(2.0, "lisi");
//        sortedSet.addScore("zs", 1); //分值+1
//        Double score = sortedSet.getScore("zs"); // 获取分值
//        Integer rank = sortedSet.rank("zs"); // 获取排名
//    }
}
