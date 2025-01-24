package com.light.enums;

/**
 * @Author light
 * @Date 2023/4/24
 * @Desc 错误示范，或者叫冗余写法示范，可以看另一个枚举类的简便写法
 **/
public enum OrderTypeEnum {
    NORMAL("0001", "普通订单"),
    GROUPON("0002", "团购订单"),
    PROMOTION("0003", "促销订单"),
    ;

    final String code;
    final String name;
    public String getCode(){return code;}
    public String getName(){return name;}
    OrderTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
}
