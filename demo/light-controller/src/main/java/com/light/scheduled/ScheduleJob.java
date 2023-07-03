package com.light.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author light
 * @Date 2023/7/3
 * @Desc 定时任务
 * cron表达式详解：秒 分 时 日 月 周
 **/
@Component
public class ScheduleJob {
//    @Scheduled(cron = "0/5 * * * * ?")
    public void exec(){
        System.out.println("每隔5秒打印一次");
    }
}
