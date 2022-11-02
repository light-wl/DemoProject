package com.light.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class DemoData {

    @ExcelProperty(value = "日期")
    private String date;

    @ExcelProperty(value = "摘要")
    private String abs;

    @ExcelProperty(value = "收入")
    private String income;

    @ExcelProperty(value = "支出")
    private String expense;

    @ExcelProperty(value = "余额")
    private String balance;

    @ExcelProperty(value = "备注")
    private String remark;

}
