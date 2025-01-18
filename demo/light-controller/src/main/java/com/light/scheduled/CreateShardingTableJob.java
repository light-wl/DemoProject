package com.light.scheduled;

import com.light.mapper.DaoParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        LocalDate tableDate = LocalDateUtil.getCurrentDate(LocalDateUtil.TIMEZONE_CCT);
        for (int i = 0; i < 7; i++) {
            userMapper.createShardingTable(LocalDateUtil.localDate2Str(tableDate, LocalDateUtil.TO_DAY_SHORT));
            tableDate = tableDate.plusDays(1L);
        }
    }
}
