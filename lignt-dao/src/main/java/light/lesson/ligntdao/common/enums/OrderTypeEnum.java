package light.lesson.ligntdao.common.enums;

/**
 * @Author light
 * @Date 2023/4/24
 * @Desc 错误示范，或者叫冗余写法示范，可以看另一个枚举类的简便写法
 **/
public enum OrderTypeEnum {
    NORMAL("普通订单", "0001"),
    GROUPON("团购订单", "0002"),
    PROMOTION("促销订单", "0003"),
    ;

    private final String name;
    private final String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    OrderTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

