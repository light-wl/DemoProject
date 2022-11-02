package com.light.demo.excel;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EasyExcelDemo {


    public static void main(String[] args) {
        //文件路径
        String filePath = "/Users/light/Desktop/CashIn.xlsx";
        //读取
//        EasyExcel.read(filePath, DemoData.class, new ExcelListener()).sheet().headRowNumber(3).doRead();
        EasyExcel.read(filePath, DemoData.class, new ExcelListener()).doReadAll();
        EasyExcel.read(filePath, DemoData.class, new ExcelListener()).doReadAll();

        // 写入
        String writeName = "/Users/light/Desktop/CashOut.xlsx";
        EasyExcel.write(writeName, DemoData.class).sheet("模板").doWrite(ExcelListener.targetList);
    }


}
