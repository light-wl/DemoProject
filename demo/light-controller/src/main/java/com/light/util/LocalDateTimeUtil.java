package com.light.util;


import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.TimeZone;

public class LocalDateTimeUtil {
    public final static String TO_SECOND_LONG = "yyyy-MM-dd HH:mm:ss";
    public final static String TO_SECOND_SHORT = "yyyyMMddHHmmss";
    public final static String TO_MINUTE_LONG = "yyyy-MM-dd HH:mm";
    public final static String TO_DAY_LONG = "yyyy-MM-dd";
    public final static String TO_DAY_SHORT = "yyyyMMdd";
    public final static String TO_MONTH_LONG = "yyyy-MM";
    public final static String TO_MONTH_SHORT = "yyyyMM";
    public final static String TO_HMS = "HH:mm:ss";

    /**
     * 零时区时间
     */
    public static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT+0:00");
    /**
     * 中国沿海时间
     */
    public static final TimeZone TIMEZONE_CCT = TimeZone.getTimeZone("GMT+8:00");

    private static void localDateCommon() {
        LocalDate localDate = null;

        //获取当前年月日 2022-10-24
        localDate = LocalDate.now();
        System.out.println(localDate);

        //构造指定的年月日
        localDate = LocalDate.of(2019, 10, 11);
        System.out.println(localDate);

        int year, month, day, dayOfWeek;
        // 年2019
        year = localDate.getYear();
        System.out.println(year);

        // 月OCTOBER
        Month monthO = localDate.getMonth();
        System.out.println(monthO);

        // 月10
        month = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println(month);

        // 日11
        day = localDate.getDayOfMonth();
        System.out.println(day);

        // 星期FRIDAY
        DayOfWeek dayOfWeekO = localDate.getDayOfWeek();
        System.out.println(dayOfWeekO);

        // 星期5
        dayOfWeek = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println(dayOfWeek);

    }

    private static void localTimeCommon() {
        LocalTime localTime = null;
        int hour, minute, second;

        //获取当前时间 17:50:25.973
        localTime = LocalTime.now();
        System.out.println(localTime);

        //获取小时
        hour = localTime.getHour();
        System.out.println(hour);

        //获取分
        minute = localTime.getMinute();
        System.out.println(minute);

        //获取秒
        second = localTime.getSecond();
        System.out.println(second);
    }

    private static void localDateTimeCommon() {
        LocalDateTime localDateTime = null;

        // 获取当前时间 2022-10-24T17:50:25.973
        localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        // 创建LocalDateTime
        localDateTime = LocalDateTime.of(2022, Month.SEPTEMBER, 1, 2, 3, 4);
        System.out.println(localDateTime);
        localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(localDateTime);
        localDateTime = LocalDate.now().atTime(LocalTime.now());
        System.out.println(localDateTime);
        localDateTime = LocalTime.now().atDate(LocalDate.now());
        System.out.println(localDateTime);

        // 获取日期 2022-10-24
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);

        // 获取时间 18:34:16.533
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);

        localDateTime = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        //增加一年
        localDateTime = localDateTime.plusYears(1);
        System.out.println(localDateTime);
        //修改年为2020
        localDateTime = localDateTime.withYear(2021);
        System.out.println(localDateTime);
        //减少一个月
        localDateTime = localDateTime.minusMonths(1);
        System.out.println(localDateTime);


    }

    private static void DateTimeFormatter() {
        LocalDate localDate = null;
        // 字符串转日期
        localDate = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);
        localDate = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate);

        // 日期转字符串
    }

    // 获取当前时间
    public static LocalDateTime getCurrentTime(TimeZone timeZone) {
        return LocalDateTime.now(timeZone.toZoneId());
    }

    public static String localDate2String(LocalDate localDate, String format) {
        //localDate.toString() 默认是 yyyy-MM-dd
        if (StringUtils.hasText(format)) {
            return localDate.toString();
        }
        DateTimeFormatter sf = DateTimeFormatter.ofPattern(format);
        return sf.format(localDate);
    }

    public static String localTime2String(LocalTime localTime, String format) {
        //localTime.toString() 默认是 17:50:25.973
        if (StringUtils.hasText(format)) {
            return localTime.toString();
        }
        DateTimeFormatter sf = DateTimeFormatter.ofPattern(format);
        return sf.format(localTime);
    }

    public static void main(String[] args) {
        System.out.println(localTime2String(LocalTime.now(), TO_HMS));
    }
}
