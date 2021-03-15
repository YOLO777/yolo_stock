package com.yolo.stock.rest;


import com.yolo.stock.biz.EverydayDataBiz;
import com.yolo.stock.common.rest.BaseController;
import com.yolo.stock.entity.EverydayData;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 每日数据表
 *
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@RestController
@RequestMapping("everydayData")
@Api
public class EverydayDataController extends BaseController<EverydayDataBiz,EverydayData> {


}