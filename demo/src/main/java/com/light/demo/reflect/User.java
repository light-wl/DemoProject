package com.light.demo.reflect;

public class User {
    private static final String NAME = "tom";
    private static final Integer AGE = 10;

    public String getName() {
        return NAME;
    }

    public String getNameAndParam(String suffix) {
        return NAME + suffix;
    }

    private Integer getAge() {
        return AGE;
    }

    public static void main(String[] args) {
        System.out.println(Double.MAX_VALUE);
    }
}
