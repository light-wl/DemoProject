package com.light.controller;

import com.light.redis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @Author light
 * @Date 2023/8/25
 * @Desc 浩万科技面试题，5题
 * 1、编写代码
 * 用户用手机号登录的时候，通常需要发送验证码，怎么防止用户恶意地重复发送验证码?写下你的思路和关键代码。重点是要避免误伤。
 *
 * 2、编写代码
 * 现在有一个导出EXCEL表格数据的接口，因为数据量过大，还有关联数据查询，导出时间过长，如何解决这个问题，请写出思路和关键代码。
 *
 * 3、设计架构
 * 对于一个每天有100万笔订单的商户平台，大概几万个商户，设计一个架构或独立的服务或集群，每天凌晨生成前一天的每个运营商的对账单，包括总订单数、总金额、订单明细等；
 * 要求半小时左右生成完。
 *
 * 4、设计架构
 * 对于一个每天有100万笔订单的商户平台，需要增加多种支付方式(微信、支付宝、各种独立第三方、钱包)，支付到独立的商户(每个商户都可以配置自己的收款账户)，需要架构灵活，
 * 以及对账方便，可热更新切换，写出关键的设计思路，可以提供一些伪代码。
 *
 * 5、设计架构
 * 设计可以支撑1000万设备连接的物联网平台架构，描述技术选型，以及背后的逻辑。没有经历过可以假设，这种场景也类似直播间/游戏/聊天，
 * 海量连接的情况下如何让系统更健壮，具有扩展性、可观测性。
 **/
@Component
public class Interview {
    /**
     * 第一题：防止用户恶意地重复发送验证码
     * 思路：一般的登录网站，都是限制用户一分钟内只能发送一次短信验证码，
     * 1、因此可以根据用户手机号，将用户信息保存到Redis中，并设置过期时间为一分钟，当需要发送短信验证码时，在redis中查看，是否存在该用户即可。
     * 2、如果在发送5次验证码后，用户依然没有成功登录，则可以进行一些惩罚措施，限制24小时使用验证码登录等等
     * */
    private final Jedis jedis = JedisUtil.getRedis();

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec(){
        String phone = "123456";
        verificatioCode(phone);
    }

    public void verificatioCode(String phone){

        // 如果之前发送过短信，则
        if(jedis.get("abcde") == null){
            System.out.println(jedis.get("abcde"));
        }
        System.out.println(jedis.get("abcde"));
        jedis.setex(phone, 60, "1");
    }

}
