package org.luffy.libs.libseext;

import java.util.Arrays;

/**
 * @since 1.1
 * @author CJ
 */
public class ArrayUtils {

    /**
     * 数组合并
     *
     * @param array1 数组1
     * @param array2 数组2
     * @param <T>    数组元数据类型
     * @return 合并以后的数组
     */
    public static <T> T[] mixedArray(T[] array1, T[] array2) {
        T[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);
        return newArray;
    }

}
