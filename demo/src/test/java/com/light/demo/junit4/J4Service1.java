package com.light.demo.junit4;

import org.springframework.stereotype.Component;

@Component
public class J4Service1 {

    private J4Service2 j4Service2;

    public int add(int one, int two){
        return one + two;
    }

    public String getBean(){
        return j4Service2.getBean();
    }
}
