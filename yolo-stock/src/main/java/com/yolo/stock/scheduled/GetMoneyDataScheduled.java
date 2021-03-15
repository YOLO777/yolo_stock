package com.yolo.stock.scheduled;

import com.yolo.stock.entity.MoneyData;
import com.yolo.stock.entity.Stock;
import com.yolo.stock.mapper.MoneyDataMapper;
import com.yolo.stock.mapper.StockMapper;
import com.yolo.stock.common.util.DateUtils;
import com.yolo.stock.common.util.mail.Mail;
import com.yolo.stock.common.util.mail.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
@EnableScheduling
public class GetMoneyDataScheduled {

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
     * 实时资金流向接口
     */
    public static final String GET_STOCK_MONEY_URL = "http://qt.gtimg.cn/q=ff_%s";
    /**
     * 每天16点定时
     */
    @Autowired
    private MoneyDataMapper moneyDataMapper;

    @Autowired
    private StockMapper stockMapper;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");

    private SimpleDateFormat ff = new SimpleDateFormat("yyyy-M-d");

    @Scheduled(cron = "0 0 16 * * ?")
    public void updateActivityStatus() {
        //判断当天是否开市
        if ("0".equals(DateUtils.isHoliday(ff.format(new Date())))) {
            List<Stock> stockList = stockMapper.selectAll();
            for (Stock stock : stockList) {
                String type = "sz";
                String stockCode = stock.getStockCode();
                if (stockCode.substring(0, 1).equals("6")) {
                    type = "sh";
                }
                try {
                    getMoneyData(type + stockCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            log.info(format.format(new Date()) + "============定时执行拉取成功");

            Mail mail = new Mail();
            mail.setSendMail(sendMail);
            mail.setReceiveMail("1272220612@qq.com");
            //授权码
            mail.setPassword(password);
            mail.setSubject("每日资金流向获取成功！");
            mail.setContent("<div>【每日资金流向获取成功】" + "<div><br>");
            try {
                SendMail.createSimpleMail(mail);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void getMoneyData(String code) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        MoneyData moneyData = new MoneyData();
        ClientHttpRequestFactory clientHttpRequestFactory = restTemplate.getRequestFactory();
        URI uri = URI.create(String.format(GET_STOCK_MONEY_URL, code));
        ClientHttpRequest clientHttpRequest = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
        ClientHttpResponse response = clientHttpRequest.execute();
        byte[] bytes = FileCopyUtils.copyToByteArray(response.getBody());
        String res = new String(bytes, "GBK");
        String responseString = res.substring(res.indexOf("\"") + 1, res.lastIndexOf("\""));
//        System.out.println(responseString);
        if (responseString.length() > 100) {
            try {
                List<String> stringList = Arrays.asList(responseString.split("~"));
                moneyData.setStockName(stringList.get(12));
                moneyData.setStockCode(stringList.get(0));
                moneyData.setMainIn(StringUtil.isEmpty(stringList.get(1)) ? null : new BigDecimal(stringList.get(1)));
                moneyData.setMainOut(StringUtil.isEmpty(stringList.get(2)) ? null : new BigDecimal(stringList.get(2)));
                moneyData.setMainNetIn(StringUtil.isEmpty(stringList.get(3)) ? null : new BigDecimal(stringList.get(3)));
                moneyData.setMainMoneyNetTotal(StringUtil.isEmpty(stringList.get(4)) ? null : new BigDecimal(stringList.get(4)));
                moneyData.setRetailIn(StringUtil.isEmpty(stringList.get(5)) ? null : new BigDecimal(stringList.get(5)));
                moneyData.setRetailOut(StringUtil.isEmpty(stringList.get(6)) ? null : new BigDecimal(stringList.get(6)));
                moneyData.setRetailNetIn(StringUtil.isEmpty(stringList.get(7)) ? null : new BigDecimal(stringList.get(7)));
                moneyData.setRetailMoneyNetTotal(StringUtil.isEmpty(stringList.get(8)) ? null : new BigDecimal(stringList.get(8)));
                moneyData.setMoneyTotal(StringUtil.isEmpty(stringList.get(9)) ? null : new BigDecimal(stringList.get(9)));
                moneyData.setTime(stringList.get(13));
                moneyData.setOneDayAgo(stringList.get(14));
                moneyData.setTwoDayAgo(stringList.get(15));
                moneyData.setThreeDayAgo(stringList.get(16));
                moneyData.setFourDayAgo(stringList.get(17));
                moneyDataMapper.insertSelective(moneyData);
            } catch (Exception e) {
                log.error("出错代码：" + moneyData.getStockCode());
            }
        }
    }
}
