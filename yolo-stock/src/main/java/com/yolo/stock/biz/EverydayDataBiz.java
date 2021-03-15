package com.yolo.stock.biz;

import com.yolo.stock.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

import com.yolo.stock.entity.EverydayData;
import com.yolo.stock.mapper.EverydayDataMapper;

/**
 * 每日数据表
 *
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@Service
public class EverydayDataBiz extends BaseBiz<EverydayDataMapper,EverydayData> {

}