package com.light.javabase;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author light
 * @Date 2023/2/27 09:01
 * @Desc List,Set,Hash
 **/
public class ListSetMapStrDemo {

    public static void main(String[] args) {
        setDemo();
    }

    /**
     * 数组删除操作：这种不会报错，但是会删不干净
     * 1、可以采用倒叙删除
     * 2、foreach 循环删除会报，java.util.ConcurrentModificationException(并发修改异常)
     */
    private static void deleteList01(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if (num % 2 == 0) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    private static void deleteList02(List<Integer> list) {
        for (Integer num : list) {
            if (num % 2 == 0) {
                boolean isTrue = list.remove(num);
            }
        }
        System.out.println(list);
    }

    private static void deleteList03(List<Integer> list) {
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
            int num = iterator.next();
            if (num % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    private static void listDemo(){

    }

    /**
     * String 操作
     * */
    private static void stringDemo(){
        StringBuffer buffer = new StringBuffer();
    }


    /**
     * Set操作
     * */
    public static void setDemo(){
        HashSet<String> hashSet = new HashSet<>();
        System.out.println(hashSet.add("set"));
        System.out.println(hashSet.add("set"));

        TreeSet<String> treeSet = new TreeSet<>();

    }
}
