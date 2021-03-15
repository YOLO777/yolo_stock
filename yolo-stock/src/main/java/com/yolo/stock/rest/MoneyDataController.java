package com.yolo.stock.rest;


import com.yolo.stock.biz.MoneyDataBiz;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.rest.BaseController;
import com.yolo.stock.entity.MoneyData;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 资金流向
 *
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@RestController
@RequestMapping("moneyData")
public class MoneyDataController extends BaseController<MoneyDataBiz,MoneyData> {

    @GetMapping("getMoneyData/{code}")
    @ApiOperation("")
    public ObjectRestResponse getData(@PathVariable("code") String code) throws IOException {
        return baseBiz.getData(code);
    }
}