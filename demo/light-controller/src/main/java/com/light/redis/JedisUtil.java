package com.light.redis;

//import redis.clients.jedis.GeoUnit;
//import redis.clients.jedis.Jedis;
//
//import javax.annotation.PostConstruct;
//import java.util.*;

public class JedisUtil {
//    private static volatile Jedis jedis;
//
//    @PostConstruct
//    public static void connect() {
//        //单例模式，从redis 连接池中获取
//        if (jedis == null) {
//            synchronized (JedisUtil.class) {
//                if (jedis == null) {
//                    jedis = JedisPoolConfig.getJedis();
//                }
//            }
//        }
//
//    }
//
//    /**
//     * 查看所有的key：keys *
//     */
//    public static Jedis getRedis() {
//        return jedis;
//    }
//
//    /**
//     * 分布式锁
//     * */
//    public void distributedLock(){
//
//    }
//
//    /**
//     * 操作string类型的key
//     * string 是最基本的类型,而且 string 类型是二进制安全的。意思是 redis 的 string 可以 包含任何数据。
//     * 比如 jpg 图片或者序列化的对象。从内部实现来看其实 string 可以看作 byte数组,最大上限是 1G 字节
//     */
//    public static void testString() {
//        jedis.set("name", "abel");
//        // set 多个key and value
//        jedis.mset("name", "abel", "age", "23", "qq", "123244232");
//        //age  + 1
//        jedis.incr("age");
//        System.out.println(jedis.get("name"));
//        // delete key
//        jedis.del("name");
//        jedis.close();
//    }
//
//    /**
//     * 操作map
//     * hash 是一个 string 类型的 field 和 value 的映射表。添加,删除操作都是 O(1)(平均)。
//     * hash 特别适合用于存储对象。相对于将对象的每个字段存成单个 string 类型。将一个对象存储在 hash 类型中会占用更少的内存,并且可以更方便的存取整个对象。
//     */
//    public static void testMap() {
//        Map<String, String> map = new HashMap<>();
//        map.put("address", "上海");
//        map.put("name", "abel");
//        map.put("age", "23");
//        jedis.hmset("user", map);
//
//        // 从map 中取出 value，单值取value
//        String str = jedis.hget("user", "address");
//
//        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变
//        List<String> getmap = jedis.hmget("user", "address");
//        System.out.println(getmap);
//        List<String> getmap2 = jedis.hmget("user", "address", "age");
//        System.out.println(getmap2);
//
//        //删除map中的某个键值
//        jedis.hdel("user", "age");
//
//        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
//
//        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
//        System.out.println("all keys ： " + jedis.hkeys("user"));//返回map对象中的所有key
//        System.out.println("all values ： " + jedis.hvals("user"));//返回map对象中的所有value
//
//        //获取 user 中的所有key
//        Set<String> keys = jedis.hkeys("user");
//        keys.stream().forEach(x -> System.out.println("key: " + x));
//    }
//
//    /**
//     * list 链表
//     * list 是一个链表结构,可以理解为一个每个子元素都是 string 类型的双向链表
//     */
//    private static void testList() {
//        //移除 lists 中所有的内容
//        jedis.del("lists");
//
//        // 向key lists 链表头部添加字符串元素
//        jedis.lpush("lists", "abel3");
//        jedis.lpush("lists", "abel2");
//        jedis.lpush("lists", "abel1");
//        // 向key lists 链表尾部添加字符串元素
//        jedis.rpush("lists", "abel4");
//        jedis.rpush("lists", "abel5");
//
//        //获取lists 的长度
//        System.out.println(jedis.llen("lists"));
//        //按顺序输出链表中所有元素
//        System.out.println(jedis.lrange("lists", 0, -1));
//        //闭区间，超出索引界限也没关系，会打印全部
//        System.out.println(jedis.lrange("lists", 0, 10));
//
//        //修改列表中指定下标2 的值  为abelLset
//        jedis.lset("lists", 2, "abelLset");
//        System.out.println(jedis.lrange("lists", 0, -1));
//
//
//        //插入两个 abel2 为了后面的删除
//        jedis.lpush("lists", "abel2");
//        jedis.lpush("lists", "abel2");
//        // 从 lists 中删除 3 个 value = abel2 的元素 , 可以不连续
//        // 当删除 count = 0 个 时则删除全部 value = abel2 的元素
//        jedis.lrem("lists", 0, "abel2");
//        System.out.println(jedis.lrange("lists", 0, -1));
//
//    }
//
//    /**
//     * set 是无序集合,最大可以包含(2 的 32 次方-1)40多亿个元素。
//     * set 的是通过 hash table 和数组实现的, 所以添加,删除,查找的复杂度都是 O(1)。hash table 会随着添加或者删除自动的调整大小。
//     * 基本操作：sadd,srem,smembers,scard,sunion,sdiff,sinter
//     */
//    public static void testSet() {
//        jedis.sadd("person", "abel1");
//        jedis.sadd("person", "abel2");
//        jedis.sadd("person", "abel3");
//        jedis.sadd("person", "abel4");
//        jedis.sadd("person", "abel4");
//
//        System.out.println("初始集合数据" + jedis.smembers("person"));
//
//        // 从 person 中 移除 abel4
//        jedis.srem("person", "abel4");
//        System.out.println("srem删除abel4后: " + jedis.smembers("person"));
//
//
//        System.out.println("判断 abels 是否是 person 集合的元素：" + jedis.sismember("person", "abels"));
//
//        System.out.println("返回集合中的一个随机元素：" + jedis.srandmember("person"));
//
//        System.out.println("返回集合的元素个数：" + jedis.scard("person"));
//
//        //集合操作，并集，交集，差集
//        String dateOne = "user:id:20200803";
//        String dateTwo = "user:id:20200804";
//        jedis.del(dateOne);
//        jedis.del(dateTwo);
//        jedis.sadd(dateOne, "1");
//        jedis.sadd(dateOne, "2");
//        jedis.sadd(dateOne, "3");
//        jedis.sadd(dateOne, "4");
//        System.out.println("集合1：" + jedis.smembers(dateOne));
//
//
//        jedis.sadd(dateTwo, "4");
//        jedis.sadd(dateTwo, "5");
//        jedis.sadd(dateTwo, "6");
//        System.out.println("集合2：" + jedis.smembers(dateTwo));
//
//
//        //并集
//        System.out.println("并集：" + jedis.sunion(dateOne, dateTwo));
//
//        //交集
//        System.out.println("交集：" + jedis.sinter(dateOne, dateTwo));
//
//        //差集（获取一个集合中有的元素而其他集合没有的元素）
//        System.out.println("差集：" + jedis.sdiff(dateOne, dateTwo));
//
//    }
//
//    /**
//     * sorted
//     * sorted set 是有序集合,它在 set 的基础上增加了一个顺序属性,这一属性在添加修 改元素的时候可以指定,每次指定后,会自动重新按新的值调整顺序。
//     * 可以理解了有两列的 mysql 表,一列存 value,一列存顺序。
//     * <p>
//     * sort set和set类型一样，也是string类型元素的集合，也没有重复的元素，不同的是sort set每个元素都会关联一个权，
//     * 通过权值可以有序的获取集合中的元素添加，删除，查找的复杂度都是O(1)
//     */
//    public static void testSortSet() {
//        System.out.println(jedis.flushDB());
//        jedis.zadd("sortKey", 300, "abel");
//        jedis.zadd("sortKey", 20, "mysql");
//        jedis.zadd("sortKey", 40, "redis");
//
//        // 按权值从小到大排序
//        System.out.println(jedis.zrange("sortKey", 0, -1));
//        // 按权值从大到小排序
//        System.out.println(jedis.zrevrange("sortKey", 0, -1));
//
//        // 元素个数
//        System.out.println("元素个数：" + jedis.zcard("sortKey"));
//        // 元素abel 的 下标
//        System.out.println("元素xxx 的 下标：" + jedis.zscore("sortKey", "abel"));
//
//        // 删除元素 abel
////        jedis.zrem("sortKey", "abel");
//
//        //权值 0-100的总数
//        System.out.println("0-100 的总数： " + jedis.zcount("sortKey", 0, 100));
//        //给元素 redis 的 权值 + 50
//        System.out.println("给元素的 权值  + 50： " + jedis.zincrby("sortKey", 50, "redis"));
//        //权值在0-100的值
//        System.out.println("权值在0-100的值： " + jedis.zrangeByScore("sortKey", 0, 100));
//        //返回 mysql 的权值的排名，从0开始计数
//        System.out.println(jedis.zrank("sortKey", "mysql"));
//        // 输出整个集合值
//        System.out.println("输出整个集合值： " + jedis.zrange("sortKey", 0, -1));
//    }
//
//    /**
//     * 我们就可以选择 Bitmap。这是 Redis 提供的扩展数据类型。我来给你解释一下它的实现原理。
//     * Bitmap 本身是用 String 类型作为底层数据结构实现的一种统计二值状态的数据类型。
//     * String 类型是会保存为二进制的字节数组，所以，Redis 就把字节数组的每个 bit 位利用起来，用来表示一个元素的二值状态。
//     * 你可以把 Bitmap 看作是一个 bit 数组。
//     */
//    public static void testBitMap() {
//        String key = "uid:sign:3000:202008";
//        jedis.setbit(key, 2, String.valueOf(1));
//        System.out.println("查看用户在3号的签到状况，1在0否：" + jedis.getbit(key, 2));
//
//        System.out.println("统计该用户在 8 月份的签到次数:" + jedis.bitcount(key));
//    }
//
//    /**
//     * HyperLogLog 是一种用于统计基数的数据集合类型，它的最大优势就在于，当集合元素数量非常多时，它计算基数所需的空间总是固定的，而且还很小。
//     * HyperLogLog 的统计规则是基于概率完成的，所以它给出的统计结果是有一定误差的，标准误算率是 0.81%。这也就意味着，
//     * 你使用 HyperLogLog 统计的 UV 是 100 万，但实际的 UV 可能是 101 万。
//     */
//    public static void testHyperLogLog() {
//        String key = "page1:uv";
//        jedis.pfadd(key, "user1", "user2", "user3");
//
//        System.out.println("查询该页面的访问量：" + jedis.pfcount(key));
//    }
//
//    /**
//     * 面向 LBS（Location-Based Service，位置信息服务） 应用的 GEO 数据类型,GEO 类型的底层数据结构就是用 Sorted Set 来实现的。
//     * GEOADD 命令：用于把一组经纬度信息和相对应的一个 ID 记录到 GEO 类型集合中；
//     * GEORADIUS 命令：会根据输入的经纬度位置，查找以这个经纬度为中心的一定范围内的其他元素。当然，我们可以自己定义这个范围。
//     */
//    public static void testGeo() {
//        String key = "cars:locations";
//        jedis.geoadd(key, 116.034579, 39.030452, String.valueOf(33));
//
//        // 查找以这个经纬度为中心的 5 公里内的车辆信息
//        jedis.georadius(key, 116.054579, 39.030452, 5, GeoUnit.KM);
//    }
}
