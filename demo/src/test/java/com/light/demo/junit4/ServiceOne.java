package com.light.demo.junit4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceOne {

    @Autowired
    private ServiceTwo serviceTwo;

    public int add(int one, int two){
        return one + two;
    }

    public String getBean(){
        return serviceTwo.getBean();
    }
}
