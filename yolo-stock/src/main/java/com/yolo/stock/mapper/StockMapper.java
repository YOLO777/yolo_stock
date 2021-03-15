package com.yolo.stock.mapper;


import com.yolo.stock.entity.Stock;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 股票表
 * 
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
public interface StockMapper extends Mapper<Stock> {

    void updateByStockCode(@Param("stock") Stock stock);

    void updateIndByStockCode(@Param("stockCode") String stockCode, @Param("indCode") String indCode);
}
