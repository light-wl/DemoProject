package com.light.demo.junit;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;

/**
 * 采用了JUnit4的包进行的Mock测试
 */
public class MockitoTest {

    /**
     * @Mock 注解可以理解为对 mock 方法的一个替代。
     * 使用该注解时，要使用MockitoAnnotations.initMocks 方法，让注解生效,
     * 比如放在@Before方法中初始化。 比较优雅优雅的写法是 在类上面使用 @RunWith(MockitoJUnitRunner.class)
     * 它可以自动执行MockitoAnnotations.initMocks 方法。
     */

    @Test
    public void mockClassTest() {
        Random mockRandom = mock(Random.class);

        // 默认值: mock 对象的方法的返回值默认都是返回类型的默认值
        System.out.println(mockRandom.nextBoolean()); // false
        System.out.println(mockRandom.nextInt()); // 0
        System.out.println(mockRandom.nextDouble()); // 0.0

        // mock: 指定调用 nextInt 方法时，永远返回 100
        when(mockRandom.nextInt()).thenReturn(100);
        Assert.assertEquals(100, mockRandom.nextInt());
        Assert.assertEquals(100, mockRandom.nextInt());
    }

    @Test
    public void helloWorldTest() {
        // mock DemoDao instance
        ServiceTwo mockServiceTwo = mock(ServiceTwo.class);

        // 使用 mockito 对 getDemoStatus 方法打桩
        when(mockServiceTwo.getDemoStatus()).thenReturn(1);

        // 调用 mock 对象的 getDemoStatus 方法，结果永远是 1
        Assert.assertEquals(1, mockServiceTwo.getDemoStatus());

        // mock DemoService
        ServiceOne mockServiceOne = new ServiceOne(mockServiceTwo);
        Assert.assertEquals(1, mockServiceOne.getDemoStatus());
    }


    @Test
    public void mockInterfaceTest() {
        List mockList = mock(List.class);

        // 接口的默认值：和类方法一致，都是默认返回值
        Assert.assertEquals(0, mockList.size());
        Assert.assertEquals(null, mockList.get(0));

        // 注意：调用 mock 对象的写方法，是没有效果的
        mockList.add("a");
        Assert.assertEquals(0, mockList.size());      // 没有指定 size() 方法返回值，这里结果是默认值
        Assert.assertEquals(null, mockList.get(0));   // 没有指定 get(0) 返回值，这里结果是默认值

        // mock值测试
        when(mockList.get(0)).thenReturn("a");          // 指定 get(0)时返回 a
        Assert.assertEquals(0, mockList.size());        // 没有指定 size() 方法返回值，这里结果是默认值
        Assert.assertEquals("a", mockList.get(0));      // 因为上面指定了 get(0) 返回 a，所以这里会返回 a
        Assert.assertEquals(null, mockList.get(1));     // 没有指定 get(1) 返回值，这里结果是默认值
    }

    /**
     * 1、spy 的参数是对象示例，mock 的参数是 class。
     * 2、被 spy 的对象，调用其方法时默认会走真实方法。mock 对象不会
     */
    @Test
    public void test_spy() {

        ServiceOne spyExampleService = spy(new ServiceOne());

        // 默认会走真实方法
        Assert.assertEquals(3, spyExampleService.add(1, 2));

        // 打桩后，不会走了
        when(spyExampleService.add(1, 2)).thenReturn(10);
        Assert.assertEquals(10, spyExampleService.add(1, 2));

        // 但是参数不匹配的调用，依然走真实方法
        Assert.assertEquals(3, spyExampleService.add(2, 1));

    }

}
