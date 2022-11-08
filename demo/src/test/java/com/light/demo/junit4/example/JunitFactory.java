package com.light.demo.junit4.example;

import org.springframework.stereotype.Service;

@Service
public class JunitFactory {
    public String getBean(){
        return "bean";
    }
}
