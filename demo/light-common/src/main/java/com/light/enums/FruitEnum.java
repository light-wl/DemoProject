package com.light.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：light
 * @date ：2025/2/18 10:52:47
 * @description :
 */
@Getter
@AllArgsConstructor
public enum FruitEnum {
    // 枚举实例，包含 code 和 name
    APPLE(1, "苹果"),
    BANANA(2, "香蕉"),
    ORANGE(3, "橙子");

    // 定义 code 和 name 属性
    private final int code;
    private final String name;


    // 根据 code 返回对应的 name 的方法
    public static String getNameByCode(int code) {
        // 遍历枚举类的所有实例
        for (FruitEnum fruit : FruitEnum.values()) {
            // 比较 code
            if (fruit.getCode() == code) {
                return fruit.getName();
            }
        }
        // 如果没有找到匹配的 code，返回 null
        return null;
    }
}

