package com.light.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class LocalDateUtil {
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAT = "DAT";
    public static final String HOUR = "HOUR";
    public static final String MINITE = "MINITE";
    public static final String SECOND = "SECOND";
    public final static String TO_SECOND_LONG = "yyyy-MM-dd HH:mm:ss";
    public final static String TO_SECOND_SHORT = "yyyyMMddHHmmss";
    public final static String TO_MINUTE_LONG = "yyyy-MM-dd HH:mm";
    public final static String TO_DAY_LONG = "yyyy-MM-dd";
    public final static String TO_DAY_SHORT = "yyyyMMdd";
    public final static String TO_MONTH_LONG = "yyyy-MM";
    public final static String TO_MONTH_SHORT = "yyyyMM";
    public final static String TO_HMS = "HH:mm:ss";
    public final static String ZONE_ID_SHANGHAI = "Asia/Shanghai";
    public final static String ZONE_ID_NEWYORK = "America/New_York";

    /**
     * 日期转字符串-不带时区
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 日期转字符串-带时区
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @param zoneId        国际时区 Locale.CHINA
     * @return String 返回格式化的日期
     */
    public static String changeZone(LocalDateTime localDateTime, ZoneId zoneId, String pattern) {
        LocalDateTime newLocalDateTime = changeZone(localDateTime, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return newLocalDateTime.format(formatter);
    }

    public static LocalDateTime changeZone(LocalDateTime localDateTime, ZoneId zoneId) {
        ZoneId defaultZoneId = ZoneId.systemDefault(); //获取系统默认时区
        ZonedDateTime zonedDateTime = localDateTime.atZone(defaultZoneId);
        ZonedDateTime newYorkDateTime = zonedDateTime.withZoneSameInstant(zoneId); //获取不同时区，但是相同的0时区时间点
        return newYorkDateTime.toLocalDateTime();
    }


    // 获取当前时间
    public static LocalDateTime getCurrentTime() {
        ZoneId zoneId = ZoneId.of(ZONE_ID_SHANGHAI);
        return LocalDateTime.now(zoneId);
    }

    // 获取当前时间-带时区
    public static LocalDateTime getCurrentTime(ZoneId zoneId) {
        return LocalDateTime.now(zoneId);
    }

    public static LocalDate changeLocalDate(LocalDate localDate, String type, Integer num) {
        switch (type) {
            case YEAR:
                return localDate.plusYears(num);
            case MONTH:
                return localDate.plusMonths(num);
            case DAT:
                return localDate.plusDays(num);
        }
        return localDate;
    }

    public static LocalDateTime changeLocalDateTime(LocalDateTime localDateTime, String type, Integer num) {
        switch (type) {
            case HOUR:
                return localDateTime.plusHours(num);
            case MINITE:
                return localDateTime.plusMinutes(num);
            case SECOND:
                return localDateTime.plusSeconds(num);
        }
        return localDateTime;
    }

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


    public static void main(String[] args) {
        System.out.println(1);
    }

}

/**
 * LocalDateTime.now()：不格式化输出【2025-01-17T22:42:09.600】
 * Instant 代表一个瞬时的时间点值对象，它从1970-01-01T00:00:00Z点毫秒计算的，是不可变的，并且是线程安全的。
 */


