package com.yolo.stock.scheduled;

import com.yolo.stock.common.util.StockExcel;
import com.yolo.stock.entity.EverydayData;
import com.yolo.stock.entity.Stock;
import com.yolo.stock.mapper.EverydayDataMapper;
import com.yolo.stock.mapper.StockMapper;
import com.yolo.stock.common.util.DateUtils;
import com.yolo.stock.common.util.mail.Mail;
import com.yolo.stock.common.util.mail.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 功能描述
 *
 * @param
 * @author YOLO
 * @date 2020-11-13
 * @return
 */
@Component
@EnableScheduling
@Slf4j
public class GetEveryDayDataScheduled implements SchedulingConfigurer {
    /**
     * 发送邮箱
     */
    @Value("${mail.sendMail}")
    private String sendMail;
    /**
     * 邮箱授权码
     */
    @Value("${mail.password}")
    private String password;

    /**
     * 每天16点半
     */
    private String cron = "0 30 16 * * ?";

    public static final String GET_STOCK_URL = "http://qt.gtimg.cn/q=%s";

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private EverydayDataMapper everydaydataMapper;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");

    private SimpleDateFormat ff = new SimpleDateFormat("yyyy-M-d");

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            //判断当天是否开市
            if ("0".equals(DateUtils.isHoliday(ff.format(new Date())))) {
                List<Stock> stockList = stockMapper.selectAll();
                List<EverydayData> everydayDataList = new ArrayList<>();
                for (Stock stock : stockList) {
                    String type = "sz";
                    String stockCode = stock.getStockCode();
                    if (stockCode.substring(0, 1).equals("6")) {
                        type = "sh";
                    }
                    try {
                        EverydayData everydayData = getEverydayData(type + stockCode);
                        everydayDataList.add(everydayData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                log.info("执行规则：" + cron);
                log.info(format.format(new Date()) + "============定时执行拉取成功");
                Mail mail = new Mail();
                mail.setSendMail(sendMail);
                mail.setReceiveMail("1272220612@qq.com");
                //授权码
                mail.setPassword(password);
                mail.setSubject("每日盘口数据获取成功！");
                mail.setContent("<div>【每日盘口数据获取成功】local" +"<div><br>");
                try {
                    SendMail.createSimpleMail(mail);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, (TriggerContext triggerContext) -> {
            //设置执行时间，
            CronTrigger cronTrigger = new CronTrigger(cron);
            Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
            return nextExecutionTime;

        });
    }

    public EverydayData getEverydayData(String code) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        EverydayData everydaydata = new EverydayData();
        ClientHttpRequestFactory clientHttpRequestFactory = restTemplate.getRequestFactory();
        URI uri = URI.create(String.format(GET_STOCK_URL, code));
        ClientHttpRequest clientHttpRequest = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
        ClientHttpResponse response = clientHttpRequest.execute();
        byte[] bytes = FileCopyUtils.copyToByteArray(response.getBody());
        String responseString = new String(bytes, "GBK");
        if (responseString.length() > 100) {
            try{
            List<String> stringList = Arrays.asList(responseString.split("~"));
            everydaydata.setStockName(stringList.get(1));
            everydaydata.setStockCode(stringList.get(2));
            everydaydata.setNowPrice(StringUtil.isEmpty(stringList.get(3)) ? null : new BigDecimal(stringList.get(3)));
            everydaydata.setYesterdayPrice(StringUtil.isEmpty(stringList.get(4)) ? null : new BigDecimal(stringList.get(4)));
            everydaydata.setTodayOpenPrice(StringUtil.isEmpty(stringList.get(5)) ? null : new BigDecimal(stringList.get(5)));
            everydaydata.setVolumeCount(StringUtil.isEmpty(stringList.get(6)) ? null : Integer.valueOf(stringList.get(6)));
            everydaydata.setOutOrderCount(StringUtil.isEmpty(stringList.get(7)) ? null : Integer.valueOf(stringList.get(7)));
            everydaydata.setInnerOrderCount(StringUtil.isEmpty(stringList.get(8)) ? null : Integer.valueOf(stringList.get(8)));
            everydaydata.setBuyOnePrice(StringUtil.isEmpty(stringList.get(9)) ? null : new BigDecimal(stringList.get(9)));
            everydaydata.setBuyOneCount(StringUtil.isEmpty(stringList.get(10)) ? null : Integer.valueOf(stringList.get(10)));
            everydaydata.setBuyTwoPrice(StringUtil.isEmpty(stringList.get(11)) ? null : new BigDecimal(stringList.get(11)));
            everydaydata.setBuyTwoCount(StringUtil.isEmpty(stringList.get(12)) ? null : Integer.valueOf(stringList.get(12)));
            everydaydata.setBuyThreePrice(StringUtil.isEmpty(stringList.get(13)) ? null : new BigDecimal(stringList.get(13)));
            everydaydata.setBuyThreeCount(StringUtil.isEmpty(stringList.get(14)) ? null : Integer.valueOf(stringList.get(14)));
            everydaydata.setBuyFourPrice(StringUtil.isEmpty(stringList.get(15)) ? null : new BigDecimal(stringList.get(15)));
            everydaydata.setBuyFourCount(StringUtil.isEmpty(stringList.get(16)) ? null : Integer.valueOf(stringList.get(16)));
            everydaydata.setBuyFivePrice(StringUtil.isEmpty(stringList.get(17)) ? null : new BigDecimal(stringList.get(17)));
            everydaydata.setBuyFiveCount(StringUtil.isEmpty(stringList.get(18)) ? null : Integer.valueOf(stringList.get(18)));
            everydaydata.setSaleOnePrice(StringUtil.isEmpty(stringList.get(19)) ? null : new BigDecimal(stringList.get(19)));
            everydaydata.setSaleOneCount(StringUtil.isEmpty(stringList.get(20)) ? null : Integer.valueOf(stringList.get(20)));
            everydaydata.setSaleTwoPrice(StringUtil.isEmpty(stringList.get(21)) ? null : new BigDecimal(stringList.get(21)));
            everydaydata.setSaleTwoCount(StringUtil.isEmpty(stringList.get(22)) ? null : Integer.valueOf(stringList.get(22)));
            everydaydata.setSaleThreePrice(StringUtil.isEmpty(stringList.get(23)) ? null : new BigDecimal(stringList.get(23)));
            everydaydata.setSaleThreeCount(StringUtil.isEmpty(stringList.get(24)) ? null : Integer.valueOf(stringList.get(24)));
            everydaydata.setSaleFourPrice(StringUtil.isEmpty(stringList.get(25)) ? null : new BigDecimal(stringList.get(25)));
            everydaydata.setSaleFourCount(StringUtil.isEmpty(stringList.get(26)) ? null : Integer.valueOf(stringList.get(26)));
            everydaydata.setSaleFivePrice(StringUtil.isEmpty(stringList.get(27)) ? null : new BigDecimal(stringList.get(27)));
            everydaydata.setSaleFiveCount(StringUtil.isEmpty(stringList.get(28)) ? null : Integer.valueOf(stringList.get(28)));
//            everydaydata.setLastestDeal(new BigDecimal(stringList.get(29)));
            everydaydata.setTime(stringList.get(30));
            everydaydata.setUpOrDown(StringUtil.isEmpty(stringList.get(31)) ? null : new BigDecimal(stringList.get(31)));
            everydaydata.setQuoteChange(StringUtil.isEmpty(stringList.get(32)) ? null : new BigDecimal(stringList.get(32)));
            everydaydata.setHighestPrice(StringUtil.isEmpty(stringList.get(33)) ? null : new BigDecimal(stringList.get(33)));
            everydaydata.setLowestPrice(StringUtil.isEmpty(stringList.get(34)) ? null : new BigDecimal(stringList.get(34)));
            everydaydata.setPriceInfo(stringList.get(35));
            everydaydata.setTurnoverRate(StringUtil.isEmpty(stringList.get(38)) ? null : new BigDecimal(stringList.get(38)));
            everydaydata.setPeRatio(StringUtil.isEmpty(stringList.get(39)) ? null : new BigDecimal(stringList.get(39)));
            everydaydata.setAmplitude(StringUtil.isEmpty(stringList.get(43)) ? null : new BigDecimal(stringList.get(43)));
            everydaydata.setCirculationMarketValue(StringUtil.isEmpty(stringList.get(44)) ? null : new BigDecimal(stringList.get(44)));
            everydaydata.setCirculationMarketValueTotal(StringUtil.isEmpty(stringList.get(45)) ? null : new BigDecimal(stringList.get(45)));
            everydaydata.setPbRatio(StringUtil.isEmpty(stringList.get(46)) ? null : new BigDecimal(stringList.get(46)));
            everydaydata.setHighLimitPrice(StringUtil.isEmpty(stringList.get(47)) ? null : new BigDecimal(stringList.get(47)));
            everydaydata.setLowLimitPrice(StringUtil.isEmpty(stringList.get(48)) ? null : new BigDecimal(stringList.get(48)));
            everydaydata.setQuantityRatio(StringUtil.isEmpty(stringList.get(49)) ? null : new BigDecimal(stringList.get(49)));
            everydaydata.setAveragePrice(StringUtil.isEmpty(stringList.get(51)) ? null : new BigDecimal(stringList.get(51)));
            everydaydata.setPeRatioMove(StringUtil.isEmpty(stringList.get(52)) ? null : new BigDecimal(stringList.get(52)));
            everydaydata.setPeRatioStatic(StringUtil.isEmpty(stringList.get(53)) ? null : new BigDecimal(stringList.get(53)));
            everydaydata.setVolume(StringUtil.isEmpty(stringList.get(57)) ? null : new BigDecimal(stringList.get(57)));
            everydaydataMapper.insertSelective(everydaydata);
            Stock stock = new Stock();
            stock.setStockCode(everydaydata.getStockCode());
            stock.setNowPrice(everydaydata.getNowPrice());
            stock.setPeRatio(everydaydata.getPeRatio());
            stock.setPbRatio(everydaydata.getPbRatio());
            stock.setPeRatioMove(everydaydata.getPeRatioMove());
            stock.setPeRatioStatic(everydaydata.getPeRatioStatic());
            stock.setCirculationMarketValue(everydaydata.getCirculationMarketValue());
            stock.setCirculationMarketValueTotal(everydaydata.getCirculationMarketValueTotal());
            stockMapper.updateByStockCode(stock);
            }catch (Exception e){
                log.error("出错代码："+everydaydata.getStockCode());
            }
        }
        return everydaydata;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
