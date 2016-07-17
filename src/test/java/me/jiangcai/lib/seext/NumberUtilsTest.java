package me.jiangcai.lib.seext;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
public class NumberUtilsTest {
    private Random random = new Random();

    @Test
    public void uuid() {
        int count = 100;
        while (count-- > 0) {
            String hash = NumberUtils.hash62(UUID.randomUUID());
            doHash(hash);
        }
    }

    @Test
    public void hash64int() throws Exception {
        int count = 100;
        while (count-- > 0) {
            String hash = NumberUtils.hash62(random.nextInt());
            doHash(hash);
        }
    }

    private void doHash(String hash) {
        assertThat(hash)
                .isNotNull()
                .isNotEmpty();
        System.out.println(hash);
    }

    @Test
    public void hash641() throws Exception {
        NumberUtils.hash62(Long.MAX_VALUE);
        NumberUtils.hash62(Long.MIN_VALUE);
        int count = 100;
        while (count-- > 0) {
            String hash = NumberUtils.hash62(random.nextLong());
            doHash(hash);
        }
    }

}