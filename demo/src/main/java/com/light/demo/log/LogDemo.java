package com.light.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class LogDemo {
    public static final String NAME = "name";
    private Integer demo(Integer age){
        return age;
    }
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(100);
        System.out.println(bigDecimal.toString());
    }
}
