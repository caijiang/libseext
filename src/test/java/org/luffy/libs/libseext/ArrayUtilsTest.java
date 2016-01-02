package org.luffy.libs.libseext;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.UUID;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
/**
 * @author CJ
 */
public class ArrayUtilsTest {

    private Random random = new Random();

    @Test
    public void testMixedArray() throws Exception {
        // 0 with many
        // 0 with 1
        // many with 0
        // many with 1
        // 0 with 0
        mixedArray(0, random.nextInt(20) + 1);
        mixedArray(0, 1);
        mixedArray(random.nextInt(20) + 1, 0);
        mixedArray(1, 0);
        mixedArray(0, 0);
        mixedArray(1, 1);
        mixedArray(random.nextInt(20) + 1, random.nextInt(20) + 1);
    }

    private void mixedArray(int array1Length, int array2Length) {
        Object array1 = Array.newInstance(String.class, array1Length);
        for (int i = 0; i < array1Length; i++) {
            Array.set(array1, i, UUID.randomUUID().toString());
        }
        Object array2 = Array.newInstance(String.class, array2Length);
        for (int i = 0; i < array2Length; i++) {
            Array.set(array2, i, UUID.randomUUID().toString());
        }

        ///

        Object newArray = ArrayUtils.mixedArray((Object[]) array1, (Object[]) array2);
        // check
        for (int i = 0; i < array1Length; i++) {
            assertThat(Array.get(newArray,i))
                    .isEqualTo(Array.get(array1,i));

        }
        for (int i = 0; i < array2Length; i++) {
            assertThat(Array.get(newArray,i+array1Length))
                    .isEqualTo(Array.get(array2,i));
        }
    }
}