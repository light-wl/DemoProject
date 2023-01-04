package com.light.demo.junit5;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class ServiceOneTest {

    /**
     * 测试私有方法
     */

    @Test
    public void addTest() {
        ServiceOne serviceOne = new ServiceOne();
        Class<ServiceOne> cal = ServiceOne.class;

        try {
            Method method = cal.getDeclaredMethod("add", int.class, int.class);
            method.setAccessible(true);
            Object obj = method.invoke(serviceOne, 1, 2);

            Assert.assertEquals(3, obj);
        } catch (Exception e) {
            Assert.fail("-----");
        }
    }
}
