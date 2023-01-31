package com.light.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Scope(value = "prototype")
public class ServiceOne {
    @Autowired
    private ServiceTwo serviceTwo;

    public void getService() {
        System.out.println(serviceTwo);
    }

    public static void main(String[] args) {

    }

    private static void bigdecimal(){
        double one = 1.1234;
        BigDecimal scale = new BigDecimal(one);
        System.out.println(scale);
        System.out.println(scale.setScale(4, BigDecimal.ROUND_UNNECESSARY));
    }

    private static void count() {
        for (int i = 1; i < 100000; i++) {
            long temp = i * i + 19;
            long sqrt = (long) Math.sqrt(temp);
            if (Math.pow(sqrt, 2) == temp) {
                System.out.println(String.format("value=%d, %d", i, sqrt));
            }
        }
    }

}
