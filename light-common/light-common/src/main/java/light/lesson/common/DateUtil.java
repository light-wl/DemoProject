package light.lesson.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author light
 * @Date 2025/1/16
 * @Desc 日期工具类
 **/
public class DateUtil {


    /**
     * 1、获取日期中的某数值。如获取月份
     * 2、增加日期中某类型的某数值。如增加日期
     * 3、str2Date
     * 4、date2Str*/

    /**
     * 获取日期中的某数值。如获取月份，年，日
     *
     * @param date     日期
     * @param dateType 日期格式：可以是数字，也可以传 Calendar.YEAR 点进去是枚举类，
     * @return 数值
     */
    public static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 日期格式：可以是数字，也可以传 Calendar.YEAR 点进去是枚举类，
     * @param amount   数值:增加数量，可为负数
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 字符串转日期
     */
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

    /**
     * 日期转字符串
     */
    public static String date2Str(Date date, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * 计算相差的天数
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static long getIntervalDays(Date date, Date otherDate) {
        long diffInMillis = Math.abs(date.getTime() - otherDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        return diff;
    }

    /**
     * 生成一个日期
     * 年：从1900年开始算
     * 月：从0开始计算，0为1月
     */
    public static Date generate(int year, int month, int day) {
        Date date = new Date(year - 1900, month - 1, day);
        return date;
    }


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
    }
}

/**
 * 时区的概念：说明date拿到的时间戳时不带时区的，但是只要一转化，就会有时区，那么是哪个时区呢？
 * 1、Date本身没有时区概念：查看源码可以知道, Date对象中存储的是一个long型变量
 * 这个变量的值为自1997-01-01 00:00:00(GMT)至Date对象记录时刻所经过的毫秒数
 * 可以通过getTime()方法,获取这个变量值,且这个变量值和时区没有关系
 * 全球任意地点同时执行new Date().getTime()获取到的值相同

 * 2、格式化Date对象成字符串, 涉及时区
 * 不管是调用Date对象的toString方法, 还是使用SimpleDateFormat的format方法去格式化Date对象,或者使用parse解析字符串成Date对象都会涉及到时区,
 * 也就是说Date对象没有时区概念, 但是格式化Date对象, 或者解析字符串成Date对象时, 是有时区概念的

 * 1. 所属的包和引入的 Java 版本
 * java.util.Date 属于 Java 的旧日期时间 API，它从 Java 的早期版本就存在。
 * java.time.LocalDate 属于 Java 8 引入的新日期时间 API，位于 java.time 包中。
 * <p>
 * 2. 可变性（Mutability）
 * java.util.Date 是可变的（mutable），这意味着你可以更改 Date 对象的值。
 * java.time.LocalDate 是不可变的（immutable），一旦创建，就不能更改。
 * <p>
 * 3. 功能和方法
 * java.util.Date 提供的方法较少，主要用于获取当前日期和时间、日期时间的加减等。
 * java.time.LocalDate 提供的方法更丰富，包括日期的比较、日期的加减等。
 * <p>
 * 4. 时区处理
 * java.util.Date 包含了时区信息，它实际上是一个 java.util.Date 对象，表示从 1970 年 1 月 1 日 00:00:00 GMT 到当前的毫秒数。
 * java.time.LocalDate 不包含时区信息，它只表示一个日期，没有时间信息。
 * <p>
 * 5. 格式化和解析
 * java.util.Date 格式化和解析通常需要 SimpleDateFormat 类的帮助。
 * java.time.LocalDate 格式化和解析通常使用 DateTimeFormatter 类。
 */

