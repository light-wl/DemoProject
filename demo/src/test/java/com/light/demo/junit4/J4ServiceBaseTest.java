package com.light.demo.junit4;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
public class J4ServiceBaseTest {

    /**
     * 生命周期：@BeforeClass,@AfterClass,@Before,@After
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("在单元测试执行前调用，只执行一次");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("在单元测试执行后调用，只执行一次");
    }

    @Before
    public void before() {
        System.out.println("在任何测试方法执行前调用");
    }

    @After
    public void after() {
        System.out.println("在任何测试方法执行后调用");
    }

    /**
     * 忽略该测试方法
     */
    @Ignore
    @Test
    public void ignore() {
        System.out.println("该方法被忽略");
    }

    /**
     * 断言测试
     */
    @Test
    public void assertEqualsTest() {
        System.out.println("测试等值断言");
        assertEquals("当断言失败后，打出该提示信息", 2, 1 + 1);
        assertFalse(false);
        assertTrue(true);
        assertNotNull("");
        assertNull(null);
    }


}
