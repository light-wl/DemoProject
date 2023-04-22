package com.light.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimesUtil {
    public static final String yyyyMMdd = "yyyyMMdd";

    public static String getCurrDate(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrLocalDate() {
        return null;
    }

    public static LocalDate str2LocalDate(String date, String format) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, fmt);
    }

    public static String localDate2Str(LocalDate date, String format) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        return date.format(fmt);
    }

    public static LocalDate changeDay(LocalDate localDate, Integer num) {
        return localDate.plusDays(num);
    }
}
