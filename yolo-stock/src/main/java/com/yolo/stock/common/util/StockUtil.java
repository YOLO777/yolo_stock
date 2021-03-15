package com.yolo.stock.common.util;

import com.yolo.stock.entity.MoneyData;
import com.yolo.stock.entity.Stock;
import com.yolo.stock.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class StockUtil {

    public static final String GET_STOCK_URL = "http://qt.gtimg.cn/q=%s";


    public static final String GET_PK_URL = "http://qt.gtimg.cn/q=s_pksh600519";

    /**
     * 实时资金流向接口
     */
    public static final String GET_STOCK_MONEY_URL = "http://qt.gtimg.cn/q=ff_%s";

    @Autowired
    private StockMapper stockMapper;

    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestFactory clientHttpRequestFactory = restTemplate.getRequestFactory();
//        URI uri =URI.create(GET_PK_URL);
        URI uri =URI.create(String.format(GET_STOCK_URL,"sz300727"));
//        restTemplate.headForHeaders(uri).add("aa","bb");
        ClientHttpRequest clientHttpRequest = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
        ClientHttpResponse response =clientHttpRequest.execute();
        System.out.println(response.getStatusCode());
        byte[] bytes=FileCopyUtils.copyToByteArray(response.getBody());
        String res=new String(bytes, "GBK");
        System.out.println(res);
        /* 找出指定的2个字符在 该字符串里面的 位置 */
        String result = res.substring(res.indexOf("\"")+1, res.lastIndexOf("\""));
        List<String> stringList = Arrays.asList(result.split("~"));
        for (int i = 0; i <stringList.size() ; i++) {
            System.out.println(i+":"+ stringList.get(i));
        }
    }

    public void getStock(String code) throws IOException {
        Stock stock = new Stock();
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestFactory clientHttpRequestFactory = restTemplate.getRequestFactory();
        URI uri = URI.create(String.format(GET_STOCK_URL, code));
        ClientHttpRequest clientHttpRequest = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
        ClientHttpResponse response = clientHttpRequest.execute();
        System.out.println(response.getStatusCode());
        byte[] bytes = FileCopyUtils.copyToByteArray(response.getBody());
        String responseString = new String(bytes, "GBK");
        System.out.println(responseString);
    }

    public static MoneyData getMoneyData(String code) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        MoneyData moneyData = new MoneyData();
        ClientHttpRequestFactory clientHttpRequestFactory = restTemplate.getRequestFactory();
        URI uri = URI.create(String.format(GET_STOCK_MONEY_URL, code));
        ClientHttpRequest clientHttpRequest = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
        ClientHttpResponse response = clientHttpRequest.execute();
        byte[] bytes = FileCopyUtils.copyToByteArray(response.getBody());
        String res = new String(bytes, "GBK");
        String responseString = res.substring(res.indexOf("\"")+1, res.lastIndexOf("\""));
        System.out.println(responseString);
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
            } catch (Exception e) {
                log.error("出错代码：" + moneyData.getStockCode());
            }
        }
        return moneyData;
    }

}
