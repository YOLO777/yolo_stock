package com.yolo.stock.entity;

import com.yolo.stock.common.util.excel.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


/**
 * 股票表
 * 
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@Data
@Table(name = "stock")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 主键
     */
    @Id
    private Integer id;
	
	
    /**
     * 股票类型 0-主板 1-深成 2-创业板 3-科创板
     */
    @Column(name = "stock_type")
    private Integer stockType;
	
	
    /**
     * 股票代码
     */
    @Column(name = "stock_code")
    @ExcelField(title = "上市公司代码", align = 1, sort = 4)
    private String stockCode;
	
	
    /**
     * 股票名称
     */
    @Column(name = "stock_name")
    @ExcelField(title = "上市公司简称", align = 1, sort = 5)
    private String stockName;
	
	
    /**
     * 历史名称
     */
    @Column(name = "stock_old_name")
    @ExcelField(title = "上市公司简称", align = 1, sort = 3)
    private String stockOldName;
	
	
    /**
     * 创建者
     */
    @Column(name = "create_by")
    private Integer createBy;
	
	
    /**
     * 更新者
     */
    @Column(name = "update_by")
    private Integer updateBy;
	
	
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
	
	
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
	
	
    /**
     * 备注
     */
    @Column(name = "remark_")
    @ExcelField(title = "上市公司简称", align = 1, sort = 2)
    private String remark;
	
	
    /**
     * 是否有效
     */
    @Column(name = "enable_")
    private Integer enable;

    /**
     * 3 当前价格
     */
    @Column(name = "now_price")
    private BigDecimal nowPrice;

    /**
     * 39市盈率
     */
    @Column(name = "pe_ratio")
    private BigDecimal peRatio;

    /**
     * 46市净率
     */
    @Column(name = "pb_ratio")
    private BigDecimal pbRatio;

    /**
     * 52 市盈率（动）
     */
    @Column(name = "pe_ratio_move")
    private BigDecimal peRatioMove;


    /**
     * 53 市盈率（静）
     */
    @Column(name = "pe_ratio_static")
    private BigDecimal peRatioStatic;

    /**
     * 44流通市值（万）
     */
    @Column(name = "circulation_market_value")
    private BigDecimal circulationMarketValue;


    /**
     * 45总市值（万）
     */
    @Column(name = "circulation_market_value_total")
    private BigDecimal circulationMarketValueTotal;

    /**
     * 行业id
     */
    @Column(name="ind_id")
    private Integer indId;

    /**
     * 行业代码
     */
    @Transient
    @ExcelField(title = "行业大类代码", align = 1, sort = 2)
    private String indCode;
	
}
