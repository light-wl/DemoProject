package com.light.demo.junit;

import java.util.Random;

public class DemoDao {

    public int getDemoStatus(){
        return new Random().nextInt();
    }
}

