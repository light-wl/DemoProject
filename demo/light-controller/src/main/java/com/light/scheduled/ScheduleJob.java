package com.light.scheduled;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author light
 * @Date 2023/7/3
 * @Desc 定时任务
 * cron表达式详解：秒 分 时 日 月 周
 **/
@Component
public class ScheduleJob {
    private List<ScheduledUserInfo> userInfoList;

//    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        if (userInfoList == null) {
            userInfoList = new ArrayList<>();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        ScheduledUserInfo userInfo = new ScheduledUserInfo();
                        userInfoList.add(userInfo);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }


        System.out.println("每隔5秒打印一次");
    }
}
