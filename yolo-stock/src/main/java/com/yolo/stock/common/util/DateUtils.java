package com.yolo.stock.common.util;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateUtils {

    /**
     * {
     * "reason":"Success",
     * "result":{
     * "data":{
     * "animalsYear":"鼠",
     * "weekday":"星期五",
     * "lunarYear":"辛丑年",
     * "lunar":"十一月十八",
     * "year-month":"2021-1",
     * "date":"2021-1-1",
     * "desc":"1月1日至3日放假，共3天",
     * "holiday":"元旦",
     * "suit":"搬家.结婚.入宅.领证.订婚.入学.求嗣.纳财.捕捉.嫁娶.纳采.移徙.竖柱.栽种.斋醮.求财.开仓",
     * "avoid":"装修.开业.开工.安床.出行.安葬.上梁.开张.旅游.修坟.赴任.破土.修造.祈福.祭祀.开市.纳畜.启钻.伐木.盖屋.经络.造桥.筑堤"
     * }* 	},
     * "error_code":0
     * }
     *
     * @param httpArg
     * @return 0-工作日 1-双休日 2-节假日
     */

    public static String isHoliday(String httpArg) {
//        String httpUrl="http://www.easybots.cn/api/holiday.php";  已失效
        String httpUrl = "http://v.juhe.cn/calendar/day";
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?date=" + httpArg + "&key=66349650e1ac71c3e029abf14908e4cc";
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            String result = sbf.toString();
            Map<String, Object> map = JSON.parseObject(result);
            Map<String, Object> result1 = (Map<String, Object>) map.get("result");
            Map<String, String> data = (Map<String, String>) result1.get("data");
            String res = "0";
//            if(!(data.get("holiday")).equals("")){
//                res="2";
//            }else{
            if (data.get("weekday").equals("星期六") || data.get("weekday").equals("星期日")) {
                res = "1";
            } else {
                res = "0";
            }
//            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-M-d");
        return f.format(date);
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
//        System.out.println(format(sdf.parse( " 2008-07-10 19:20:00 " )));
        System.out.println(format(new Date()));
        System.out.println(isHoliday("2021-5-9"));
    }

}
