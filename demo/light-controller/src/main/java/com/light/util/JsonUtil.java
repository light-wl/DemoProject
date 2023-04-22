package com.light.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import java.util.Map;

public class JsonUtil {
    public static String map2Json(Map map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public static String Object2Json(Object object){
        return JSON.toJSONString(object);
    }

}
