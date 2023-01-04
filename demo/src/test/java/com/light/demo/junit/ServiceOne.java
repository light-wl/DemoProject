package com.light.demo.junit;

public class ServiceOne {

    private ServiceTwo serviceTwo;

    public ServiceOne() {
    }

    public ServiceOne(ServiceTwo serviceTwo) {
        this.serviceTwo = serviceTwo;
    }

    public int getDemoStatus() {
        return serviceTwo.getDemoStatus();
    }

    public int add(int a, int b) {
        return a + b;
    }
}

