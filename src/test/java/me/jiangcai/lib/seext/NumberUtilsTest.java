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
    public void hash62() {
        int count = 100;
        while (count-- > 0) {
//            long x = Math.abs(random.nextLong());
            long x = random.nextLong();
//            x = 10101010;
            System.out.println(x);
            final String hash = NumberUtils.hash62(x);
            System.out.println(hash);
//            System.out.println(NumberUtils.recoverHash62(hash));
            assertThat(NumberUtils.recoverHash62(hash))
                    .isEqualTo(x);
        }
    }

    @Test
    public void uuid2() {
        int count = 100;
        while (count-- > 0) {
            UUID uuid = UUID.randomUUID();
            String uuidName = uuid.toString();
            final String hash62 = NumberUtils.hash62(random.nextBoolean() ? uuidName : uuidName.replace("-", ""), true);
//            System.out.println(hash62);
            UUID uuid2 = NumberUtils.hash62ToUUID(hash62);
            assertThat(uuid2)
                    .isEqualTo(uuid);
        }
    }

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