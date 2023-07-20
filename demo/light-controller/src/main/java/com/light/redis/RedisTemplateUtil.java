package com.light.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        UseSet();
    }

    /**
     * Set：聚合统计
     */
    public void UseSet() {
        //1、通过redisTemplate设置值
        redisTemplate.boundSetOps("setKey").add("setValue1", "setValue2", "setValue3");

        //2、通过BoundValueOperations设置值
        BoundSetOperations<String, String> setKey = redisTemplate.boundSetOps("setKey");
        setKey.add("setValue1", "setValue2", "setValue3");

        //3、通过ValueOperations设置值
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        setOps.add("setKey", "SetValue1", "setValue2", "setValue3");

        // 聚合统计，统计每天的新增用户，一个存所有用户，一个存每天的登录用户，做一个差集
        redisTemplate.boundSetOps("user:id:all").add("001", "002", "003");
        redisTemplate.boundSetOps("user:id:20230101").add("001", "004");
        Set<String> result = redisTemplate.opsForSet().difference("user:id:all", "user:id:20230101");
        System.out.println(result);


    }

    /**
     * ZSet：排序统计
     */
    public void UseZSet() {
    }

    /**
     * BitMap：位图，用来进行打卡统计;
     */
    public void UseBitMap() {
    }

    /**
     * HyperLogLog：基数统计，用来统计UV
     * 注意：如果需要精确统计，则最好使用Set，如果数据量过大，则可以使用这个，但是会存在一定的误差，但是不会很大1%左右
     */
    public void UseHyperLogLog() {
    }

    /**
     * GEO：地理位置，存储经纬度
     */
    public void UseGEO() {
    }
}
