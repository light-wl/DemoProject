package com.light.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author light
 * @Date 2023/4/24
 * @Desc
 **/
@Data
public class ValidationModel {

    /**
     * 空和非空检查
     */
    @NotBlank(message = "只能用于字符串不为 null ，并且字符串 .trim() 以后 length 要大于 0")
    private String one;

    @NotEmpty(message = "集合对象的元素不为 0 ，即集合不为空")
    private List<String> two;

    @NotNull(message = "不能为 null")
    private String three;

    @Null(message = "必须为 null")
    private String four;

    /**
     * 数值检查
     */
    @DecimalMax(value = "100", message = "被注释的元素必须是一个数字，其值必须小于等于指定的最大值。")
    @DecimalMin(value = "0", message = "被注释的元素必须是一个数字，其值必须大于等于指定的最小值.")
    private Integer five;

    @Max(value = 100, message = "该字段的值只能小于或等于该值。")
    @Min(value = 0, message = "该字段的值只能大于或等于该值。")
    private Integer eight;

    @Positive(message = "判断正数。")
    @PositiveOrZero(message = "判断正数或 0")
    private Integer six;

    @Negative(message = "判断负数")
    @NegativeOrZero(message = "判断负数或 0")
    private Integer seven;

    /**
     * 长度检查
     * */
    @Size(max = 10, min = 1, message = "检查字段的 size 是否在 min 和 max 之间，可以是字符串、数组、集合、Map 等")
    private String nine;

    /**
     * 自定义注解校验
     * */


    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
