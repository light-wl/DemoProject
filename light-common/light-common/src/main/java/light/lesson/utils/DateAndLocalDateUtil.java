package light.lesson.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author light
 * @Date 2025/1/18
 * @Desc Date和LocalDate相互转化
 **/
public class DateAndLocalDateUtil {

    /**
     * Date转LocalDate
     * 默认为东八区时间
     */
    public static LocalDate Date2LocalDate(Date date) {
        ZoneId toZoneId = ZoneId.of("Asia/Shanghai");
        LocalDate localDate = Date2LocalDate(date, toZoneId);
        return localDate;
    }

    /**
     * Date转LocalDate
     * 自定义时区
     */
    public static LocalDate Date2LocalDate(Date date, ZoneId toZoneId) {
        //1. instant : 2020-01-17T08:52:59.235Z
        Instant instant = date.toInstant(); //Zone : 默认是UTC+0时区
        //2. localDate : 2020-01-17
        LocalDate localDate = instant.atZone(toZoneId).toLocalDate();
        return localDate;
    }

    /**
     * Date转LocalDateTime,返回字符串
     * 默认为东八区时间
     */
    public static String Date2LocalDateTime(Date date, String pattern) {
        ZoneId toZoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = Date2LocalDateTime(date, toZoneId);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * Date转LocalDateTime
     * 默认为东八区时间
     */
    public static LocalDateTime Date2LocalDateTime(Date date) {
        ZoneId toZoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = Date2LocalDateTime(date, toZoneId);
        return localDateTime;
    }

    /**
     * Date转LocalDateTime
     * 自定义时区
     */
    public static LocalDateTime Date2LocalDateTime(Date date, ZoneId toZoneId) {
        //1. instant : 2020-01-17T08:52:59.235Z
        Instant instant = date.toInstant();  //Zone : 默认是UTC+0时区

        //3. LocalDateTime: 2020-01-17T16:52:59.235
        LocalDateTime localDateTime = instant.atZone(toZoneId).toLocalDateTime();
        return localDateTime;
    }


}
