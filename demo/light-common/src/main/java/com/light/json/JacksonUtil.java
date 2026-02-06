package com.light.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author light
 * @date 2025/11/23
 * @desc
 **/
public class JacksonUtil {

    private void Object2Json(Object object) {
        ObjectMapper mapper = new ObjectMapper();

        // 1、对象转 String
        // User user = new User("Alice", 25);
        // String json = mapper.writeValueAsString(user);
        // 输出: {"name":"Alice","age":25}

        // 2、String 转对象
        String json = "{\"name\":\"Bob\",\"age\":30}";
        // User user = mapper.readValue(json, User.class);
        // user.getName() == "Bob"
    }

    public static void main(String[] args) {
        System.out.println("123");
    }
}
