package com.light.redis;

import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//
//import javax.annotation.Resource;

/**
 * @Author light
 * @Date 2023/9/18
 * @Desc
 **/
@Slf4j
public class RedisLock {
//    public static final String LOG_TRACE_KEY = "logTraceId";
//
//    private static final String LOCK_NAME = "financial_turnover_to_accounting_entry_job_lock";
//
//    @Resource
//    private RedissonClient redissonClient;
//
//    public void redisLock() {
//        long startTime = System.currentTimeMillis();
//        RLock lock = redissonClient.getLock(LOCK_NAME);
//        boolean result = false;
//        try {
//            result = lock.tryLock();
//            if (!result) {
//                return;
//            }
//            log.info("根据流水记分录定时任务开始");
//
//        } catch (Exception e) {
//            log.error("根据流水记分录定时任务开始", e);
//        } finally {
//            if (result) {
//                long spendTime = System.currentTimeMillis() - startTime;
//                log.info("根据流水记分录定时任务执行时间 spendTime={}", spendTime);
//                try {
//                    if (lock.isLocked()) {
//                        lock.unlock();
//                    }
//                } catch (Exception e) {
//                    log.error("根据流水记分录定时任务redis解锁异常", e);
//                }
//            }
//        }
//    }

}
