package com.light.enums;

/**
 * @Author light
 * @Date 2023/4/24
 * @Desc 枚举类的文件夹不能是enum，这是关键字，不能用做包名
 **/
public enum OrderTypeEnum {
    NORMAL("普通订单", "0001"),
    GROUPON("团购订单","0002"),
    PROMOTION("促销订单","0003"),
    ;

    final String name;
    final String code;
    public String getName(){return name;}
    public String getCode(){return code;}
    OrderTypeEnum(String name, String code){
        this.name = name;
        this.code = code;
    }
}
