package me.jiangcai.lib.seext;

import java.util.Random;

/**
 * @author CJ
 * @since 1.3.1
 */
public class EnumUtils {

    /**
     * @param type   传入枚举类型
     * @param values 可选预订选择的范围
     * @param <T>    枚举范型
     * @return 随机枚举
     */
    public static <T extends Enum> T randomEnum(Class<T> type, T... values) {
        if (values == null) {
            values = type.getEnumConstants();
        }
        int value = new Random().nextInt(values.length);
        return values[value];
    }

    /**
     * @param type 传入枚举类型
     * @param <T>  枚举范型
     * @return 随机枚举
     */
    public static <T extends Enum> T randomEnum(Class<T> type) {
        return randomEnum(type, (T[]) null);
    }

}
