package com.light;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
//@Slf4j
public class LogDemo {
    //直接加注解可以不写下面这句
    private static final Logger log = LoggerFactory.getLogger("类名");

    public static String output = "";

    public static void foo(int i) throws Exception {
        try {
            if (i == 1) throw new Exception();
        }  finally {
            output += "3";
        }
        output += "4";
    }

    public static void Test() throws Exception {
        String str = "123456";
        System.out.println(str.substring(0,3));
    }

    public static void main(String[] args) throws Exception {
        Test();
    }


}
