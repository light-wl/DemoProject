package com.light.demo.junit4.example;

import com.light.demo.junit.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Example {

    @Autowired
    private JunitFactory junitFactory;

    public int add(int one, int two){
        return one + two;
    }

    public String getBean(){
        return junitFactory.getBean();
    }
}
