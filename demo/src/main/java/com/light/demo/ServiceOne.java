package com.light.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class ServiceOne {
    @Autowired
    private ServiceTwo serviceTwo;

    public void getService() {
        System.out.println(serviceTwo);
    }
}
