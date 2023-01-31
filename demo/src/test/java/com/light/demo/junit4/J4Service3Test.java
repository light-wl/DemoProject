package com.light.demo.junit4;

import com.light.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * 启动Spring项目，进行单元测试，可以识别@Autowired
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class J4Service3Test {
    @Autowired
    private Map<String, IService> serviceMap;

    @Autowired
    ServiveImplOne serviveImplOne;

    @Test
    public void test() {
        System.out.println(serviveImplOne.add(1, 1));
        System.out.println(serviceMap.getClass().getName());
        for (Map.Entry<String, IService> map : serviceMap.entrySet()) {
            System.out.println(map.getKey() + "=========>" + map.getValue().getClass().getName());
        }
    }
}
