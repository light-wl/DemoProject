package com.light.demo.redis;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ServiveImplOne implements IService{
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
