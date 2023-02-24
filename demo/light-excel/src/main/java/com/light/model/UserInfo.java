package com.light.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:26
 **/
@Data
public class UserInfo {
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
