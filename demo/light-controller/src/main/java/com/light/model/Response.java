package com.light.model;

import lombok.Data;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 15:41
 **/

//@Data 不添加这个注解会导致response的时候get不到值，导致返回为空
@Data
public class Response {
    String retCode = "0000";
    String retMsg = "成功";
}
