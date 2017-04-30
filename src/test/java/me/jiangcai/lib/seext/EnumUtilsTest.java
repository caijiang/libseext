package me.jiangcai.lib.seext;

import org.junit.Test;

/**
 * @author CJ
 */
public class EnumUtilsTest {
    @Test
    public void randomEnum() throws Exception {
        System.out.println(EnumUtils.randomEnum(TestE.class));
        System.out.println(EnumUtils.randomEnum(TestE.class));
        System.out.println(EnumUtils.randomEnum(TestE.class));
    }

}