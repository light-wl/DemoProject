package com.light.demo.junit4.example;

import com.light.demo.util.DateTimeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest({DateTimeUtil.class})
public class ExampleTest {


    /**
     * 1、最基础的test类，可以构造实例，直接调用方法
     */
    @Test
    public void Test01() {
        Example example = new Example();
        int ans = example.add(1, 1);
        Assert.assertEquals(2, ans);
        System.out.println(Integer.MAX_VALUE);
    }

    /**
     * 2、当测试类中存在 @Autowired 时：
     * 如果使用 Example example = new Example();，则里面的注解不会生效，即没有注入对象，会报空指针异常；
     * 需要使用Mock；并添加两个注解：
     *
     * @RunWith(PowerMockRunner.class)
     * @PowerMockRunnerDelegate(SpringRunner.class)
     */
    @InjectMocks
    private Example example;

    @Mock
    private JunitFactory junitFactory;

    @Test
    public void Test02() {
        Mockito.when(junitFactory.getBean()).thenReturn("123");
        String result = example.getBean();
        Assert.assertEquals("123", result);
    }

    /**
     * 3、当测试类中需要Mock 常量、静态、私有方法等，静态方法用的比较多，需要用到：
     * @PrepareForTest
     */
    @Test
    public void Test03() {
        PowerMockito.mockStatic(DateTimeUtil.class);
        PowerMockito.when(DateTimeUtil.getCurrDate(Mockito.anyString())).thenReturn("2022.11.1");
        Assert.assertEquals("2022.11.1", DateTimeUtil.getCurrDate("123"));
    }

}
