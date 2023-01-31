package com.light.demo.transaction;

/**
 * @Description 注解学习类，通过该类，列举出平时写代码需要注意的类注释规范
 * @Author light
 * @Date 2023/1/9 10:49
 **/
public class AnnotationStandard {

    /**
     * 交易处理类：这里写主要流程，不要设计太多细节，多封装一些函数
     */
    public void process() {
        //1、处理一
        checkAge(100);
        //2、处理二

        // 3、处理三
    }

    /**
     * 检查一：检查年龄是否符合
     *
     * @param age 年龄
     */
    private void checkAge(int age) {

    }

}
