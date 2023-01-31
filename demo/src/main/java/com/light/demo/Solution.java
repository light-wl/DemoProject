package com.light.demo;

/**
 * @Description
 * @Author light
 * @Date 2023/1/30 16:23
 **/


public class Solution {
    public static void main(String[] args) {
        System.out.print("".split(",").length);
    }


    public boolean checkXMatrix(int[][] grid) {
        int x = grid.length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                //判断是否对角线上的都不是 0
                if (i==j || (i + j)==(x - 1)) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                    // 判断是否不是对角线上的都是 0
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
