package com.light.redis;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.BoundSetOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SetOperations;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author light
 * @Date 2023/5/15
 * @Desc BitMap, HyperLogLog, GEO，常用来聚合统计，排序统计，二值状态统计，基数统计,UV,PV
 * UV：全称Unique Visitor，也叫独立访客量，是指通过互联网访问、浏览这个网页的自然人。1天内同一个用户多次访问该网站，只记录1次。
 * PV：全称Page View，也叫页面访问量或点击量，用户每访问网站的一个页面，记录1次PV，用户多次打开页面，则记录多次PV。往往用来衡量网站的流量。
 * Set：聚合统计;
 * ZSet：排序统计;
 * BitMap：位图，用来进行打卡统计;
 * HyperLogLog：基数统计，用来统计UV;
 * GEO：地理位置，存储经纬度
 **/
@Component
public class RedisTemplateUtil {
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    //    @Scheduled(cron = "0/5 * * * * ?")
//    public void exec() {
//        UseSet();
//    }
//
//    /**
//     * Set：聚合统计
//     */
//    public void UseSet() {
//        //1、通过redisTemplate设置值
//        redisTemplate.boundSetOps("setKey").add("setValue1", "setValue2", "setValue3");
//
//        //2、通过BoundValueOperations设置值
//        BoundSetOperations<String, String> setKey = redisTemplate.boundSetOps("setKey");
//        setKey.add("setValue1", "setValue2", "setValue3");
//
//        //3、通过ValueOperations设置值
//        SetOperations<String, String> setOps = redisTemplate.opsForSet();
//        setOps.add("setKey", "SetValue1", "setValue2", "setValue3");
//
//        // 聚合统计，统计每天的新增用户，一个存所有用户，一个存每天的登录用户，做一个差集
//        redisTemplate.boundSetOps("user:id:all").add("001", "002", "003");
//        redisTemplate.boundSetOps("user:id:20230101").add("001", "004");
//        Set<String> result = redisTemplate.opsForSet().difference("user:id:all", "user:id:20230101");
//        System.out.println(result);
//
//
//    }
//
//    /**
//     * ZSet：排序统计
//     */
//    public void UseZSet() {
//    }
//
//    /**
//     * BitMap：
//     *
//     * 1.基于最小的单位bit进行存储，所以非常省空间。
//     * 2.设置时候时间复杂度O(1)、读取时候时间复杂度O(n)，操作是非常快的。
//     * 3.二进制数据的存储，进行相关计算的时候非常快。
//     * 4.方便扩容
//     * 使用：bitmap的常见操作命令只有3个，分别是setbit、getbit、bitcount
//     * setbit key index value 这里的offest必须替换为索引，value只有0/1两种选择
//     */
//    public void UseBitMap() {
//        // 应用：用来进行打卡统计;一天对应一系列用户的签到记录，以日期作为key，用户ID作为偏移量；
//        // 1、可以统计每天的签到天数；2、也可以统计连续几天内的连续签到数
//        String key = "2023:01:01";
//        long offest = 1001L;
//        boolean value = true;
//        redisTemplate.opsForValue().setBit(key, offest, value);
//        redisTemplate.opsForValue().getBit(key, offest);
//    }
//
//    /**
//     * HyperLogLog：
//     * 应用：用来统计UV，UV:unique vistitor :独立访客量，指通过互联网访问、浏览网页的自然人。1天内同一个用户多次访问该网站，只记录1次。
//     * 注意：如果需要精确统计，则最好使用Set，如果数据量过大，则可以使用这个，但是会存在一定的误差，但是不会很大 1% 左右
//     */
//    public void UseHyperLogLog() {
//        String[] userIds = new String[1000];
//        String key = "page1";
//        redisTemplate.opsForHyperLogLog().add(key, userIds); // 存储
//        redisTemplate.opsForHyperLogLog().size(key); // 统计
//    }
//
//    /**
//     * GEO：地理位置，存储经纬度
//     */
//    public void UseGEO() {
//    }
}
