package com.light.model;

import com.light.enums.ReturnCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 15:41
 **/

//@Data 不添加这个注解会导致response的时候get不到值，导致返回为空
@Data
public class Response<T> implements Serializable {
    String retCode = "0000";
    String retMsg = "成功";
    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<T>();
        response.setRetCode(ReturnCodeEnum.SUCCESS.getRetCode());
        response.setRetMsg(ReturnCodeEnum.SUCCESS.getRetMsg());
        response.setData(data);
        return response;
    }

}
