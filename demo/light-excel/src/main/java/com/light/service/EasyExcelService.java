package com.light.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.light.model.OrderInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:31
 **/
public class EasyExcelService {

    public static void main(String[] args) {
        initData();
        String writeName = "temp.xlsx";
        EasyExcel.write(writeName, OrderInfo.class).sheet("模板").doWrite(ExcelListener.targetList);
    }

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

    public void CreateExcel() {
        // 指定输出的文件路径和文件名
        String fileName = "example.xlsx";

        // 创建 ExcelWriter 对象
        try (ExcelWriter excelWriter = new ExcelWriterBuilder().file(fileName).build()) {
            // 创建 ExcelWriterSheetBuilder 对象
//            ExcelWriterSheetBuilder excelWriterSheetBuilder = new ExcelWriterSheetBuilder(excelWriter, "Sheet1");

            // 写入数据
//            List<String> data = initData();
//            excelWriterSheetBuilder.doWrite(data);
        }
    }

    private static void initData() {
        OrderInfo info = new OrderInfo();
        info.setAbs("aa");
        info.setBalance("bb");
        info.setDate("123");
        info.setExpense("aaa");
        info.setIncome("122");
        info.setRemark("remark");
        ExcelListener.targetList.add(info);
    }
}
