package com.light.demo.junit;

import java.util.Random;

public class MockitoServiceTwo {

    public int getDemoStatus(){
        return new Random().nextInt();
    }
}

