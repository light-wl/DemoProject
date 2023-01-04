package com.light.demo.util;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Map2JsonUtil {

    /**
     * Map2Json
     */
    public static String map2Json(Map map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public static void testJson() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println(map2Json(map));
    }

    public static void testList() {
        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("c");
        list.add("a");
        list = list.stream().sorted().collect(Collectors.toList());
        System.out.println(list);
    }

    public static void testSplit(){
        String test = "1@|$2@|$3";
        String[] result = test.split("@\\|\\$");
        for(String str : result){
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        testSplit();
    }
}
