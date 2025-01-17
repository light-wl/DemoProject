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
        System.out.println(num);
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
     * */
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
     * */
    public static String date2Str(Date date, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * 计算相差的天数
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
     * */
    public static Date generate(int year, int month, int day){
        Date date = new Date(year - 1900, month-1, day);
        return date;
    }


    public static void main(String[] args) {
        Date date1 = new Date();
        Date date2 = new Date(2025 - 1900, 0, 1);
        System.out.println(date1);
        System.out.println(date2);
        long result = DateUtil.getIntervalDays(date1, date2);
        System.out.println(result);
    }
}

