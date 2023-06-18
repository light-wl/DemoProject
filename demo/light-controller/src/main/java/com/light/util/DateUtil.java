package com.light.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAT = "DAT";
    public static final String HOUR = "HOUR";
    public static final String MINITE = "MINITE";
    public static final String SECOND = "SECOND";
    public static final String TO_DAY_SHORT = "yyyyMMdd";
    public static final String TO_DAY_LONG = "yyyy-MM-dd";
    public static final String TO_DATETIME_LONG = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrDate(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date());
    }

    public static Date str2Date(String str, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date changeDate(Date date, String type, Integer num) {
        Calendar cal = Calendar.getInstance();
        switch (type) {
            case YEAR: cal.add(Calendar.YEAR, num);
            case MONTH: cal.add(Calendar.MONTH, num);
            case DAT: cal.add(Calendar.DAY_OF_YEAR, num);
            case HOUR: cal.add(Calendar.HOUR, num);
            case MINITE: cal.add(Calendar.MINUTE, num);
            case SECOND: cal.add(Calendar.SECOND, num);
        }
        return cal.getTime();
    }
}
