package com.light.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.Map;

public class JsonUtil {
    public static void main(String[] args) {
        BigDecimal two = new BigDecimal("1234.56");
        System.out.println(two);
    }
    public static String map2Json(Map map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
    public static String Object2Json(Object object){
        return JSON.toJSONString(object);
    }

}
