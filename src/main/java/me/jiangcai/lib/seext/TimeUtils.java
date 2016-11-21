package me.jiangcai.lib.seext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 提供Java8标准和原Date系的转换,<a href="https://blog.tompawlak.org/java-8-conversion-new-date-time-api">参考</a>
 *
 * @author CJ
 * @since 1.3
 */
@SuppressWarnings("WeakerAccess")
public class TimeUtils {

    public static Date toDate(LocalDateTime time, ZoneId zoneId) {
        if (zoneId == null)
            zoneId = ZoneId.systemDefault();
        return Date.from(time.atZone(zoneId).toInstant());
    }

    public static Date toDate(LocalDate date, ZoneId zoneId) {
        if (zoneId == null)
            zoneId = ZoneId.systemDefault();
        return Date.from(date.atStartOfDay(zoneId).toInstant());
    }

    public static Date toDate(Instant instant) {
        return Date.from(instant);
    }

    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        if (zoneId == null)
            zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    public static LocalDate toLocalDate(Date date, ZoneId zoneId) {
        return toLocalDateTime(date, zoneId).toLocalDate();
    }

    public static Instant toInstant(Date date) {
        return date.toInstant();
    }


}
