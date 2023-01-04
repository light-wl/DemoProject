package com.light.demo.junit4;

import com.light.demo.util.DateTimeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(PowerMockRunner.class)
//@PowerMockRunnerDelegate(SpringRunner.class)
//@PrepareForTest({DateTimeUtil.class})
//@RunWith(SpringRunner.class)

/**
 * @RunWith(OrderRunner.class) 自定义运行器，可以按照自定义顺序执行
 * @RunWith(MockitoJUnitRunner.class) 让@Mock注解生效的一种方式
 * @RunWith(PowerMockRunner.class) 当需要Mock静态方法的时候，需要使用到
 * 如果用PowerMockRunner，那么MockitoJUnitRunner 就不能用了，但不要担心， @Mock 等注解还能用。
 * @RunWith(SpringRunner.class) 启动项目，执行测试类
 * */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DateTimeUtil.class})
public class ServiceOneTest {


    /**
     * 1、最基础的test类，可以构造实例，直接调用方法
     * 要想顺序执行测试类，则需要 @RunWith(OrderRunner.class)
     */
    @Test
    @Order(1)
    public void Test01() {
        ServiceOne serviceOne = new ServiceOne();
        int ans = serviceOne.add(1, 1);
        Assert.assertEquals(2, ans);
    }

    /**
     * 2、当测试类中存在 @Autowired 时：
     * 如果使用 Example example = new Example();，则里面的注解不会生效，即没有注入对象，会报空指针异常；
     * @RunWith(MockitoJUnitRunner.class)
     * 需要使用@Mock；主类需要使用@InjectMocks
     */
    @InjectMocks
    private ServiceOne serviceOne;

    @Mock
    private ServiceTwo serviceTwo;

    @Test
    @Order(2)
    public void Test02() {
        Mockito.when(serviceTwo.getBean()).thenReturn("123");
        String result = serviceOne.getBean();
        Assert.assertEquals("123", result);
    }

    /**
     * 3、当测试类中需要Mock 常量、静态、私有方法等，静态方法用的比较多，需要用到：
     * @RunWith(PowerMockRunner.class)
     * @PrepareForTest({DateTimeUtil.class})
     */
    @Test
    @Order(3)
    public void Test03() {
        PowerMockito.mockStatic(DateTimeUtil.class);
        PowerMockito.when(DateTimeUtil.getCurrDate(Mockito.anyString())).thenReturn("2022.11.1");
        Assert.assertEquals("2022.11.1", DateTimeUtil.getCurrDate("123"));
    }

    /**
     * 4、启动spring容器，使用Bean的方式，会全部自动注入
     * @RunWith(SpringRunner.class)
     * @SpringBootTest
     */
    @Autowired
    private ServiceOne serviceOneTwo;

    @Test
    @Order(4)
    public void Test04() {
        String result = serviceOneTwo.getBean();
        Assert.assertEquals("bean", result);
    }


}
