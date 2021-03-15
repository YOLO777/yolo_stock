package com.yolo.stock.common.util;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

/**
 * 流程工具类
 * @author jhq
 * @email 1917216897@qq.com
 * @date 2019/3/4 11:00
 */
public class FlowUtils {

    /**
     * byte数组转换成base64编码
     *
     * @param b
     * @return
     */
    public static String conver2Base64(byte[] b) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b);
    }

    /**
     * 格式化时间, 把以毫秒为单位的时间转化为字符串形式 ： xx天xx时xx分xx秒
     * @param timeMillis
     * @return
     */
    public static String gainTimeStr(Long timeMillis) {
        StringBuilder timeStr = new StringBuilder();
        // 时间单位在毫秒中的体现, 精确到天
        long second = 1000;
        long minute = second * 60;
        long hour = minute * 60;
        long day = hour * 24;
        String secondStr = "";
        String minuteStr = "";
        String hourStr = "";
        String dayStr = "";
        if (timeMillis >= day) {
            dayStr = String.valueOf(timeMillis / day);
            timeMillis = timeMillis % day;
        }
        if (timeMillis >= hour) {
            hourStr = String.valueOf(timeMillis / hour);
            timeMillis = timeMillis % hour;
        }
        if (timeMillis >= minute) {
            minuteStr = String.valueOf(timeMillis / minute);
            timeMillis = timeMillis % minute;
        }
        if (timeMillis >= second) {
            secondStr = String.valueOf(timeMillis / second);
        }
        if (StringUtils.isNotEmpty(dayStr)) {
            timeStr.append(dayStr).append("天");
        }
        if (StringUtils.isNotEmpty(hourStr)) {
            timeStr.append(hourStr).append("时");
        }
        if (StringUtils.isNotEmpty(minuteStr)) {
            timeStr.append(minuteStr).append("分");
        }
        if (StringUtils.isNotEmpty(secondStr)) {
            timeStr.append(secondStr).append("秒");
        }
        return timeStr.toString();
    }

    public static void main(String[] args) {
        System.out.println(gainTimeStr(86400000L));
    }

}
