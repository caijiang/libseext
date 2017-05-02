package me.jiangcai.lib.seext;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * hash62是一种建议哈希算法,将阿拉伯数字,大小写英文作为进制。
 *
 * @author CJ
 * @since 1.3
 */
@SuppressWarnings("WeakerAccess")
public class NumberUtils {

    /**
     * All possible chars for representing a number as a String
     */
    final static char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * @param x
     * @return HASH62格式
     */
    public static String hash62(int x) {
        return hash62(Integer.toUnsignedLong(x));
    }

    /**
     * @param uuid
     * @return HASH62格式
     */
    public static String hash62(UUID uuid) {
        return hash62(uuid, false);
    }

    /**
     * @param uuid
     * @param restored 可还原的HASH62格式
     * @return HASH62格式
     */
    public static String hash62(UUID uuid, boolean restored) {
        StringBuilder buffer = hashLong(uuid.getMostSignificantBits(), 62);
        if (restored)
            buffer.append('-');
        buffer.append(hashLong(uuid.getLeastSignificantBits(), 62));
        return buffer.toString();
    }

    /**
     * @param uuid
     * @return HASH62格式
     * @see UUID#toString()
     */
    public static String hash62(String uuid, boolean restored) {
        // 8 4 4 4
        try {
            return hash62(UUID.fromString(uuid), restored);
        } catch (IllegalArgumentException ignored) {
            StringBuilder stringBuilder = new StringBuilder(uuid);
            stringBuilder.insert(8, '-');
            stringBuilder.insert(8 + 4 + 1, '-');
            stringBuilder.insert(8 + 4 + 4 + 2, '-');
            stringBuilder.insert(8 + 4 + 4 + 4 + 3, '-');
            return hash62(UUID.fromString(stringBuilder.toString()), restored);
        }
    }

    /**
     * @param hash HASH62格式
     * @return UUID
     * @see #hash62(UUID, boolean)
     */
    public static UUID hash62ToUUID(String hash) {
        String[] strings = hash.split("-");
        long m = recoverHash62(strings[0]);
        long l = recoverHash62(strings[1]);
        return new UUID(m, l);
    }

    public static long recoverHash62(String string) {
        return from(string, digits);
    }

    private static long from(String hash, char[] chars) {
        long val = 0;
        char[] hashChars = hash.toCharArray();

        for (int i = 0; i < hashChars.length; i++) {
            int n = hashChars.length - i - 1;
            int index = indexOf(chars, hashChars[i]);
            long x = (long) (Math.pow(chars.length, n) * (long) index);
            val = val + x;
        }

        BigInteger base = BigInteger.valueOf(chars.length);
        BigInteger current = BigInteger.ZERO;
        for (int i = 0; i < hashChars.length; i++) {
            int n = hashChars.length - i - 1;
            int index = indexOf(chars, hashChars[i]);
            BigInteger toX = base.pow(n).multiply(BigInteger.valueOf(index));
            current = current.add(toX);
        }
        try {
            return Long.parseLong(current.toString());
        } catch (NumberFormatException ignored) {
            return Long.parseUnsignedLong(current.toString());
        }
//        System.out.println(current);
//        return val;
    }

    private static int indexOf(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c)
                return i;
        }
        return -1;
    }

    private static StringBuilder hashLong(long input, int radix) {
        if (radix == digits.length)
            return longToNBuf(input, digits);
        if (radix > digits.length)
            throw new IllegalArgumentException("radix must less than 62");
        char[] newChars = new char[radix];
        System.arraycopy(digits, 0, newChars, 0, newChars.length);
        return longToNBuf(input, newChars);
    }

    // https://github.com/zl19861124/jeecms/blob/master/src/com/jeecms/common/util/Num62.java
    private static StringBuilder longToNBuf(long l, char[] chars) {
        int upgrade = chars.length;
        StringBuilder result = new StringBuilder();
        BigInteger current = new BigInteger(Long.toUnsignedString(l));
        //        BigInteger current = BigInteger.valueOf(Math.abs(l));

        BigInteger number = BigInteger.valueOf(upgrade);
        while (current.compareTo(BigInteger.ZERO) > 0) {
            final int figure = current.remainder(number).intValue();
            result.insert(0, chars[figure]);
            current = current.divide(number);
        }

//        l = Math.abs(l);
//        while (l > 0) {
//            int last = (int) (l % upgrade);
//            result.insert(0, chars[last]);
//            l /= upgrade;
//        }
        return result;
    }

    /**
     * 典型的百分比显示一个数字；如有必要将最多保留2位小数
     *
     * @param number 数字
     * @return 形如20%,11.15%
     * @since 1.3.2
     */
    public static String normalPercentage(Number number) {
        return normalPercentage(number, Locale.CHINA);
    }

    /**
     * 典型的百分比显示一个数字；如有必要将最多保留2位小数
     *
     * @param number 数字
     * @return 形如20%,11.15%
     * @since 1.3.2
     */
    public static String normalPercentage(Number number, Locale locale) {
        final NumberFormat percentInstance = NumberFormat.getPercentInstance(locale);
        percentInstance.setMaximumFractionDigits(2);
        return percentInstance.format(number);
    }


    public static String hash62(long input) {
        return hashLong(input, 62).toString();
        //
//        BigInteger bigInteger = toUnsignedBigInteger(input);
//        return toString(bigInteger,62);
//        111111111111111111111111111111111111111111111111111111111111111111111111
        // rombrbjfm28s0s
        // 首先按照62 bit
        // 字节是 8个 8个一起的
        // 但是我很强大62位
    }


//    public static String hashRaid32(byte[]... input){
//        Integer.p
//        "".equalsIgnoreCase()
//    }
}
