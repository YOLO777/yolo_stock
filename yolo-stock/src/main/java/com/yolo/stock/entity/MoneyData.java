package com.yolo.stock.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


/**
 * 资金流向
 * 
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@Data
@Table(name = "money_data")
public class MoneyData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 主键
     */
    @Id
    private Integer id;
	
	
    /**
     * 股票代码
     */
    @Column(name = "stock_code")
    private String stockCode;
	
	
    /**
     * 12股票名称
     */
    @Column(name = "stock_name")
    private String stockName;
	
	
    /**
     * 1主力流入
     */
    @Column(name = "main_in")
    private BigDecimal mainIn;
	
	
    /**
     * 2主力流出
     */
    @Column(name = "main_out")
    private BigDecimal mainOut;
	
	
    /**
     * 3主力净流入
     */
    @Column(name = "main_net_in")
    private BigDecimal mainNetIn;
	
	
    /**
     * 4主力净流入/资金流入流出总和
     */
    @Column(name = "main_money_net_total")
    private BigDecimal mainMoneyNetTotal;
	
	
    /**
     * 5散户流入
     */
    @Column(name = "retail_in")
    private BigDecimal retailIn;
	
	
    /**
     * 6散户流出
     */
    @Column(name = "retail_out")
    private BigDecimal retailOut;
	
	
    /**
     * 7散户净流入
     */
    @Column(name = "retail_net_in")
    private BigDecimal retailNetIn;
	
	
    /**
     * 8散户净流入/资金流入流出总和
     */
    @Column(name = "retail_money_net_total")
    private BigDecimal retailMoneyNetTotal;
	
	
    /**
     * 9资金流入流出总和1+2+5+6
     */
    @Column(name = "money_total")
    private BigDecimal moneyTotal;
	
	
    /**
     * 13时间
     */
    @Column(name = "time_")
    private String time;
	
	
    /**
     * 14前一个交易日（昨日）
     */
    @Column(name = "one_day_ago")
    private String oneDayAgo;
	
	
    /**
     * 15前前一个交易日（前日）
     */
    @Column(name = "two_day_ago")
    private String twoDayAgo;
	
	
    /**
     * 16前前前一个交易日（大前日）
     */
    @Column(name = "three_day_ago")
    private String threeDayAgo;
	
	
    /**
     * 17前前前一个交易日（大大前日）
     */
    @Column(name = "four_day_ago")
    private String fourDayAgo;
	
	
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
    private String remark;
	
	
    /**
     * 是否有效
     */
    @Column(name = "enable_")
    private Integer enable;
	
}
