package com.light.service;

import com.alibaba.excel.EasyExcel;
import com.light.model.OrderInfo;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:31
 **/
public class EasyExcelService {
        public void ExcelDemo() {
        //文件路径
        String filePath = "/Users/light/Desktop/CashIn.xlsx";
        //读取
//        EasyExcel.read(filePath, UserInfo.class, new ExcelListener()).sheet().headRowNumber(3).doRead();
        EasyExcel.read(filePath, OrderInfo.class, new ExcelListener()).doReadAll();
        EasyExcel.read(filePath, OrderInfo.class, new ExcelListener()).doReadAll();

        // 写入
        String writeName = "/Users/light/Desktop/CashOut.xlsx";
        EasyExcel.write(writeName, OrderInfo.class).sheet("模板").doWrite(ExcelListener.targetList);
    }
}
