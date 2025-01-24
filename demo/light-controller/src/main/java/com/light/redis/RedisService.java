package com.light.redis;

//import org.springframework.util.StringUtils;
//import redis.clients.jedis.Jedis;

/**
 * @Author light
 * @Date 2023/9/9
 * @Desc Redis 的使用
 **/
public class RedisService {
    /**
     * 1、先查询Redis；
     * 2、如果Redis没有，则查询MySQL；
     * 3、然后同步到Redis中；
     */
//    public String getUserNameById(String userId) {
//        JedisUtil.connect();
//        Jedis jedis = JedisUtil.getRedis();
//        String name = jedis.get(userId);
//        if (!StringUtils.hasLength(name)) {
//            name = "数据库查询";
//            jedis.set(userId, name);
//        }
//        return name;
//    }
}
