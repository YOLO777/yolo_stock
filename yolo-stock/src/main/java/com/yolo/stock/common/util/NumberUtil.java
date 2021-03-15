package com.yolo.stock.common.util;

import java.math.BigDecimal;

/**
 * 文件描述
 *
 * @author qilong.tang
 * @date 2019-08-19 10:23
 */
public class NumberUtil {
    public static BigDecimal formatDecimal(BigDecimal value){
        if(null != value){
            return new BigDecimal(Double.toString(value.doubleValue()));
        }else{
            return null;
        }
    }

    public static BigDecimal add(BigDecimal b1,BigDecimal b2){
        b1 = formatDecimal(b1);
        b2 = formatDecimal(b2);
        if(null != b1 && null != b2){
            return b1.add(b2);
        }else{
            return null;
        }
    }

    public static BigDecimal substract(BigDecimal b1,BigDecimal b2){
        b1 = formatDecimal(b1);
        b2 = formatDecimal(b2);
        if(null != b1 && null != b2){
            return b1.subtract(b2);
        }else{
            return null;
        }
    }
}
