package com.yolo.stock.mapper;


import com.yolo.stock.entity.MoneyData;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 资金流向
 * 
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
public interface MoneyDataMapper extends Mapper<MoneyData> {
    /**
     * 获取净流入前二十个股
     * @param time
     * @return
     */
    List<MoneyData> getTop20MoneyIn(@Param("time") String time);

    /**
     * 获取净流出前二十个股
     * @param time
     * @return
     */
    List<MoneyData> getTop20MoneyOut(@Param("time") String time);
}
