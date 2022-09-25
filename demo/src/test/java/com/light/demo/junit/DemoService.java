package com.light.demo.junit;

public class DemoService {

    private DemoDao demoDao;

    public DemoService() {
    }

    public DemoService(DemoDao demoDao) {
        this.demoDao = demoDao;
    }

    public int getDemoStatus() {
        return demoDao.getDemoStatus();
    }

    public int add(int a, int b) {
        return a + b;
    }
}

