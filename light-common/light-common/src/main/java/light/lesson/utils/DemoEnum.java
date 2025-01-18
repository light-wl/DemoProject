package light.lesson.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author light
 * @Date 2025/1/16
 * @Desc 枚举工具类
 **/
@Getter
@AllArgsConstructor
public enum DemoEnum {
    CLOSE(0, "关"),
    OPEN(1, "开"),
    ;

    private final int code;
    private final String name;

    // 通过 code 获取枚举名称的静态方法
    public static String getNameByCode(int code) {
        for (DemoEnum item : DemoEnum.values()) {
            if (item.getCode() == code) {
                return item.getName();
            }
        }
        throw new IllegalArgumentException("No enum constant with [" + code + "] found");
    }
}
