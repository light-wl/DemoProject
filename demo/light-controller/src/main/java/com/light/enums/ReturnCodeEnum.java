package com.light.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author light
 * @Date 2023/6/28
 * @Desc
 **/
@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {
    SUCCESS("0000", "成功"),
    ;

    final String retCode;
    final String retMsg;
}
