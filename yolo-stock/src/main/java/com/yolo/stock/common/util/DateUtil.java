package com.yolo.stock.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 获取两个时间的中间日期
     *
     * @param starttime
     * @param endtime
     * @return
     */
    public static List<String> getBetweenTime(String starttime, String endtime) {
        List<String> betweenTime = new ArrayList<String>();
        try {
            Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
            Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sdate);
            int year = sCalendar.get(Calendar.YEAR);
            int month = sCalendar.get(Calendar.MONTH);
            int day = sCalendar.get(Calendar.DATE);
            sCalendar.set(year, month, day, 0, 0, 0);

            Calendar eCalendar = Calendar.getInstance();
            eCalendar.setTime(edate);
            year = eCalendar.get(Calendar.YEAR);
            month = eCalendar.get(Calendar.MONTH);
            day = eCalendar.get(Calendar.DATE);
            eCalendar.set(year, month, day, 0, 0, 0);

            while (sCalendar.before(eCalendar)) {
                betweenTime.add(format.format(sCalendar.getTime()));
                sCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            betweenTime.add(format.format(eCalendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return betweenTime;
    }


    /**
     * 当月第一天
     *
     * @return
     */
    public static Date thisMonthFirst() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE,1);
        System.out.println(format.format(calendar.getTime()));
        return calendar.getTime();
    }

    /**
     * 六个月前的一天
     *
     * @return
     */
    public static Date sixMonthAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-6);
        return calendar.getTime();
    }

    /**
     * 一年前
     *
     * @return
     */
    public static Date oneYearAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR,-1);
        return calendar.getTime();
    }


    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Object date, String pattern) {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date,"yyyy-MM-dd");
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
        * 两个时间相差秒数
        * @author YOLO
        * @date 2020-11-26
        * @param
        * @return
    */
    public static int getTimeDelta(Date date1,Date date2){
        //单位是秒
        int timeDelta= (int) ((date1.getTime()-date2.getTime())/1000);
        return timeDelta;
    }

}
