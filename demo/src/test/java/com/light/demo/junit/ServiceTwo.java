package com.light.demo.junit;

import java.util.Random;

public class ServiceTwo {

    public int getDemoStatus(){
        return new Random().nextInt();
    }
}

