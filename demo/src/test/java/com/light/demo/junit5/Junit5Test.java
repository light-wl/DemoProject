package com.light.demo.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class Junit5Test {

    /**
     * 生命周期：@BeforeAll, @AfterAll, @BeforeEach, @AfterEach
     */
    @BeforeAll
    public static void beforeClass() {
        System.out.println("在单元测试执行前调用，只执行一次");
    }

    @AfterAll
    public static void afterClass() {
        System.out.println("在单元测试执行后调用，只执行一次");
    }

    @BeforeEach
    public void before() {
        System.out.println("在任何测试方法执行前调用");
    }

    @AfterEach
    public void after() {
        System.out.println("在任何测试方法执行后调用");
    }

    @Disabled
    @Test
    public void ignore() {
        System.out.println("该方法被忽略");
    }


    /**
     * 断言测试
     */
    @Test
    public void assertTest() {
        assumeTrue("abc".contains("a"));
        assertEquals(2, 1 + 1);
    }

    /**
     * 抛出异常测试
     * */
    @Test
    @DisplayName("自定义测试方法的名称")
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }
}
