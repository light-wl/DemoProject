package com.light.controller;


import java.util.HashMap;

/**
 * @Author light
 * @Date 2023/8/16
 * @Desc
 **/

public class LeetCode {

    public int count = 0;

    public static void main(String[] args) {
        HashMap<LeetCode, Integer> list = new HashMap<>();
        LeetCode one = new LeetCode();
        list.put(new LeetCode(), 100);
        list.get(one);
        System.out.println(list.get(one));
    }

    public int maxDistToClosest(int[] seats) {
        int leftMax = left(seats);
        int rightMax = right(seats);
        int midMax = mid(seats);
        int maxBoundry = Math.max(leftMax, rightMax);
        return  Math.max(maxBoundry, midMax);
    }

    public int left(int[] seats) {
        int len = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                len++;
            } else {
                break;
            }
        }
        return len;
    }

    public int right(int[] seats) {
        int len = 0;
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == 0) {
                len++;
            } else {
                break;
            }
        }
        return len;
    }

    public int mid(int[] seats) {
        int maxLen = 0;
        int tempLen = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                tempLen++;
            } else {
                if(tempLen > maxLen){
                    maxLen = tempLen;
                }
                tempLen = 0;
            }
        }

        if ((maxLen % 2) == 0) {
            return maxLen / 2;
        } else {
            return (maxLen + 1) / 2;
        }
    }
}
