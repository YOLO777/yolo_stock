package com.yolo.stock.vo;

import com.yolo.stock.common.util.excel.annotation.ExcelField;
import lombok.Data;

@Data
public class IndustryVo {
    /**
     * 行业大类
     */
    @ExcelField(title = "行业名称", align = 1, sort = 3)
    private String indName;


    /**
     * 行业代码
     */
    @ExcelField(title = "行业代码", align = 1, sort = 2)
    private String indCode;

    /**
     * 行业代码
     */
    @ExcelField(title = "门类名称及代码", align = 1, sort = 1)
    private String categoryNameAndCode;

    /**
     * 上市公司代码
     */
    @ExcelField(title = "上市公司代码", align = 1, sort = 4)
    private String stockCode;

    /**
     * 上市公司名称
     */
    @ExcelField(title = "上市公司名称", align = 1, sort = 5)
    private String stockName;
}
