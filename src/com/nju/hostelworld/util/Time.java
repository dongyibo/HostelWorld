package com.nju.hostelworld.util;

import com.nju.hostelworld.model.VO.RecordDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dongyibo on 2017/1/8.
 */
public class Time {

    private Time() {
    }

    /**
     * 获取一年后的日期
     *
     * @return
     */
    public static Date getDateAfterOneYear() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    /**
     * 获取一年后的日期
     *
     * @return
     */
    public static Date getDateAfterOneYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.YEAR, 1);
        return gc.getTime();
    }



    /**
     * 获取几天后的日期
     *
     * @param day
     * @return
     */
    public static Date getDateAfterDay(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    /**
     * 获取指定日期几天后的日期
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateAfterDay(Date date, int day) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DATE, day);
        return gc.getTime();
    }

    /**
     * 判断时间点冲突
     *
     * @param recordDate
     * @param date
     * @return
     */
    public static boolean isTimeConflict(RecordDate recordDate, Date date) {
        long t1 = quantification(recordDate.getStartDate());
        long t2 = quantification(recordDate.getEndDate());
        long t3 = quantification(date);
        if (t3 >= t1 && t3 < t2) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间段冲突
     *
     * @param recordDate1
     * @param recordDate2
     * @return
     */
    public static boolean isTimeConflict(RecordDate recordDate1, RecordDate recordDate2) {
        long t1 = quantification(recordDate1.getStartDate());
        long t3 = quantification(recordDate2.getStartDate());
        long t4 = quantification(recordDate2.getEndDate());
        if (t1 >= t3 && t1 < t4) {
            return true;
        }
        return false;
    }

    /**
     * 时间区间的冲突
     *
     * @param recordDate1
     * @param recordDate2
     * @return
     */
    public static boolean isTimeIntervalConflict(RecordDate recordDate1, RecordDate recordDate2) {
        long t1 = quantification(recordDate1.getStartDate());
        long t2 = quantification(recordDate1.getEndDate());
        long t3 = quantification(recordDate2.getStartDate());
        long t4 = quantification(recordDate2.getEndDate());
        if (t1 < t3) {
            if (t2 <= t3) {
                return false;
            }
        } else if (t1 > t3) {
            if (t4 <= t1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 日期量化
     *
     * @param date
     * @return
     */
    public static long quantification(Date date) {
        String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String split[] = time.split("-");
        long re = Integer.parseInt(split[0]) * 10000 + Integer.parseInt(split[1]) * 100 + Integer.parseInt(split[2]);
        return re;
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 判断时间是否准确
     *
     * @param date
     * @return
     */
    public static Result isTimeCorrect(Date date) {
        long l1 = quantification(date);
        long l2 = quantification(new Date());
        if (l1 < l2) {
            return Result.Expired;
        } else if (l1 > l2) {
            return Result.NotYet;

        }
        return Result.True;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取当前哪一天
     *
     * @return
     */
    public static int getday() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        return day;
    }

    /**
     * 封装日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date date(int year, int month, int day) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间1是否早于时间2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBefore(Date date1, Date date2) {
        long t1 = quantification(date1);
        long t2 = quantification(date2);
        if (t1 < t2) {
            return true;
        }
        return false;
    }

    /**
     * 获取年-月的格式
     * @param date
     * @return
     */
    public static String getYearMonth(Date date) {
        String s= dateFormat(date);
        String split[] = s.split("-");
        return split[0]+"-"+split[1];
    }


    public static void main(String[] args) {
        System.out.println(Time.getDateAfterOneYear(new Date()));
    }


}
