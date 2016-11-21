package me.jiangcai.lib.seext;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
public class TimeUtilsTest {

    @Test
    public void test() {
        LocalDateTime dateTime = LocalDateTime.now();
        assertThat(TimeUtils.toLocalDateTime(TimeUtils.toDate(dateTime, null), null))
                .isEqualTo(dateTime);

        LocalDate date = LocalDate.now();
        assertThat(TimeUtils.toLocalDate(TimeUtils.toDate(date, null), null))
                .isEqualTo(date);

        Instant instant = Instant.now();
        assertThat(TimeUtils.toInstant(TimeUtils.toDate(instant)))
                .isEqualTo(instant);
    }

}