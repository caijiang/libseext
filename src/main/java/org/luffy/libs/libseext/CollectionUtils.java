package org.luffy.libs.libseext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 集合工具类
 *
 * @author CJ
 */
public class CollectionUtils {

    /**
     * @param data 元素
     * @param <T>  类型
     * @return HashSet实例，包含一个元素
     */
    public static <T> Set<T> singleSet(T data) {
        HashSet<T> set = new HashSet<>();
        set.add(data);
        return set;
    }

    /**
     * @param data 元素集合
     * @param <T>  类型
     * @return HashSet实例，包含所有元素
     */
    public static <T> Set<T> mutliSet(T... data) {
        HashSet<T> set = new HashSet<>();
        Collections.addAll(set, data);
        return set;
    }

}
