package com.light.demo.junit4;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义运行器，按照指定顺序执行测试用例
 * */
public class OrderRunner extends BlockJUnit4ClassRunner {
    //测试用例的方法集合
    private static List<FrameworkMethod> testMethodList;

    public OrderRunner(Class<?> clazz) throws InitializationError{
        super(clazz);
    }

    //按照指定的顺序执行，如我这里按照数字大小顺序执行
    @Override
    protected List<FrameworkMethod> computeTestMethods(){
        if(testMethodList == null){
            testMethodList = super.computeTestMethods().stream()
                    .sorted((m1, m2) -> {
                        //根据Order注解来决定执行顺序
                        Order order1 = m1.getAnnotation(Order.class);
                        Order order2 = m2.getAnnotation(Order.class);
                        if(order1 == null || order2 == null){
                            return 0;
                        }
                        return order1.value() - order2.value();
                    }).collect(Collectors.toList());
        }
        return testMethodList;
    }
}
