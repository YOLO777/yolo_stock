package com.yolo.stock.biz;


import com.yolo.stock.common.biz.BaseBiz;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.mapper.MoneyDataMapper;
import com.yolo.stock.common.util.StockUtil;
import org.springframework.stereotype.Service;
import com.yolo.stock.entity.MoneyData;
import java.io.IOException;

/**
 * 资金流向
 *
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@Service
public class MoneyDataBiz extends BaseBiz<MoneyDataMapper,MoneyData> {
    public ObjectRestResponse getData(String code) throws IOException {
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        String type = "sz";
        if (code.substring(0, 1).equals("6")) {
            type = "sh";
        }
        MoneyData moneyData = StockUtil.getMoneyData(type+code);
        objectRestResponse.setData(moneyData);
        return objectRestResponse;

    }
}