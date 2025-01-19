package com.light.scheduled;

import com.light.mapper.DaoParamMapper;
import com.light.utils.LocalDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author light
 * @Date 2023/6/29
 * @Desc 按天分表，需要提前创建表保存数据
 **/
@Component
public class CreateShardingTableJob {
    @Autowired
    private DaoParamMapper userMapper;

    // 按天分表，每天凌晨跑一次，提前创建七张表
//    @Scheduled(cron = "0 * * * * ?")
    public void execute() {
        LocalDateTime tableDate = LocalDateTime.now();
        LocalDateUtil.localDateTimeToString(LocalDateTime.now(), LocalDateUtil.TO_DAY_SHORT);
        for (int i = 0; i < 7; i++) {
            userMapper.createShardingTable(LocalDateUtil.localDateTimeToString(tableDate, LocalDateUtil.TO_DAY_SHORT));
            tableDate = tableDate.plusDays(1L);
        }
    }
}
