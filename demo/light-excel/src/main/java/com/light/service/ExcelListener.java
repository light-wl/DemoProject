package com.light.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.light.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExcelListener extends AnalysisEventListener<UserInfo> {

    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 500;
    private static List<UserInfo> originList = new ArrayList<>();
    public static List<UserInfo> targetList = new ArrayList<>();

    @Override
    public void invoke(UserInfo demoData, AnalysisContext analysisContext) {
        if (demoData != null) {
            log.info("解析到一条数据:{}", JSON.toJSONString(demoData));
            originList.add(demoData);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        handleData();
        log.info("所有数据解析完成！");
    }

    public void handleData() {
        // 构造初始值
        String firstBalance = originList.get(0).getBalance();
        String firstDate = originList.get(1).getDate();
        if (!"01".equals(firstDate.substring(firstDate.length() - 2))) {
            firstDate = firstDate.substring(0, firstDate.length() - 2) + "01";
        }

        int i = 1;
        while (i < originList.size()) {
            UserInfo demoData = originList.get(i);
            if (demoData.getDate().equals(firstDate)) {
                firstBalance = demoData.getBalance();
                ++i;
            } else {
                UserInfo temp = new UserInfo();
                temp.setDate(firstDate);
                temp.setBalance(firstBalance);
                targetList.add(temp);
                firstDate = addOneDay(firstDate);
            }
        }

        // 补最后一条数据
        UserInfo temp = new UserInfo();
        temp.setDate(firstDate);
        temp.setBalance(firstBalance);
        targetList.add(temp);

    }

    private String addOneDay(String date) {
//        LocalDate localDate = DateTimeUtil.str2LocalDate(date, DateTimeUtil.yyyyMMdd);
//        return DateTimeUtil.localDate2Str(localDate.plusDays(1L), DateTimeUtil.yyyyMMdd);
        return "";
    }

    private void addItem() {

    }
}


