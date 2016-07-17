package me.jiangcai.lib.seext;

import java.util.UUID;

/**
 * @author CJ
 * @since 1.3
 */
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

    public static String hash62(int x) {
        return hash62(Integer.toUnsignedLong(x));
    }

    public static String hash62(UUID uuid) {
        StringBuilder buffer = hashLong(uuid.getMostSignificantBits(), 62);
        buffer.append(hashLong(uuid.getLeastSignificantBits(), 62));
        return buffer.toString();
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
        int last;
        l = Math.abs(l);
        while (l > 0) {
            last = (int) (l % upgrade);
            result.append(chars[last]);
            l /= upgrade;
        }
        return result;
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
