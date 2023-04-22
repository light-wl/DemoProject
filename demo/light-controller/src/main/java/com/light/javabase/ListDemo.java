package com.light.javabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author light
 * @Date 2023/2/27 09:01
 **/
public class ListDemo {

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
}
