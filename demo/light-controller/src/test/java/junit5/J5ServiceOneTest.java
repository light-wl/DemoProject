package junit5;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class J5ServiceOneTest {

    /**
     * 测试私有方法
     */

    @Test
    public void addTest() {
        J5ServiceOne j5ServiceOne = new J5ServiceOne();
        Class<J5ServiceOne> cal = J5ServiceOne.class;

        try {
            Method method = cal.getDeclaredMethod("add", int.class, int.class);
            method.setAccessible(true);
            Object obj = method.invoke(j5ServiceOne, 1, 2);

            Assert.assertEquals(3, obj);
        } catch (Exception e) {
            Assert.fail("-----");
        }
    }
}
