package com.light.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author light
 * @Date 2023/6/16
 * @Desc
 **/
@Service
@Slf4j
public class AsyncService {
    // 发送短信，用异步进行处理和标记
    @Async
    public void sendMsg(){
        // todo :模拟耗时5秒
        try {
            Thread.sleep(2000);
            log.info("---------------发送消息--------");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    // 添加积分，用异步进行处理和标记
    @Async
    public void addScore(){
        // todo :模拟耗时5秒
        try {
            Thread.sleep(3000);
            log.info("---------------处理积分--------");
            throw new Exception("处理积分出错");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
