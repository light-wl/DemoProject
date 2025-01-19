package light.lesson.ligntdao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author light
 * @Date 2023/6/28
 * @Desc 枚举类的文件夹不能是enum，这是关键字，不能用做包名
 * Getter 注解可以省去写 Getter 方法
 * AllArgsConstructor 可以省去写构造函数
 **/
@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {
    SUCCESS("0000", "成功"),
    ;

    private final String retCode;
    private final String retMsg;
}

