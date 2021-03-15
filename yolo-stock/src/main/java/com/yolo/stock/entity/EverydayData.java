package com.yolo.stock.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


/**
 * 每日数据表
 * 
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@Data
@Table(name = "everyday_data")
public class EverydayData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 
     */
    @Id
    private Integer id;
	
	
    /**
     * 30时间
     */
    @Column(name = "time_")
    private String time;
	
	
    /**
     * 0 证券所 0-主板 1-深成 2-创业板 3-科创板
     */
    @Column(name = "stock_exchange")
    private String stockExchange;
	
	
    /**
     * 1 股票名称
     */
    @Column(name = "stock_name")
    private String stockName;
	
	
    /**
     * 2 股票代码
     */
    @Column(name = "stock_code")
    private String stockCode;
	
	
    /**
     * 3 当前价格
     */
    @Column(name = "now_price")
    private BigDecimal nowPrice;
	
	
    /**
     * 4 昨天收盘价
     */
    @Column(name = "yesterday_price")
    private BigDecimal yesterdayPrice;
	
	
    /**
     * 5 今日开盘价
     */
    @Column(name = "today_open_price")
    private BigDecimal todayOpenPrice;
	
	
    /**
     * 6 成交量（手）
     */
    @Column(name = "volume_count")
    private Integer volumeCount;
	
	
    /**
     * 7 外盘
     */
    @Column(name = "out_order_count")
    private Integer outOrderCount;
	
	
    /**
     * 8 内盘
     */
    @Column(name = "inner_order_count")
    private Integer innerOrderCount;
	
	
    /**
     * 9 买一价
     */
    @Column(name = "buy_one_price")
    private BigDecimal buyOnePrice;
	
	
    /**
     * 10 买一量
     */
    @Column(name = "buy_one_count")
    private Integer buyOneCount;
	
	
    /**
     * 11 买二价
     */
    @Column(name = "buy_two_price")
    private BigDecimal buyTwoPrice;
	
	
    /**
     * 12 买二量
     */
    @Column(name = "buy_two_count")
    private Integer buyTwoCount;
	
	
    /**
     * 13 买三价
     */
    @Column(name = "buy_three_price")
    private BigDecimal buyThreePrice;
	
	
    /**
     * 14 买三量
     */
    @Column(name = "buy_three_count")
    private Integer buyThreeCount;
	
	
    /**
     * 15 买四价
     */
    @Column(name = "buy_four_price")
    private BigDecimal buyFourPrice;
	
	
    /**
     * 16 买四量
     */
    @Column(name = "buy_four_count")
    private Integer buyFourCount;
	
	
    /**
     * 17买五价
     */
    @Column(name = "buy_five_price")
    private BigDecimal buyFivePrice;
	
	
    /**
     * 18 买五量
     */
    @Column(name = "buy_five_count")
    private Integer buyFiveCount;
	
	
    /**
     * 19卖一价
     */
    @Column(name = "sale_one_price")
    private BigDecimal saleOnePrice;
	
	
    /**
     * 20卖一量
     */
    @Column(name = "sale_one_count")
    private Integer saleOneCount;
	
	
    /**
     * 21卖二价
     */
    @Column(name = "sale_two_price")
    private BigDecimal saleTwoPrice;
	
	
    /**
     * 22卖二量
     */
    @Column(name = "sale_two_count")
    private Integer saleTwoCount;
	
	
    /**
     * 23卖三价
     */
    @Column(name = "sale_three_price")
    private BigDecimal saleThreePrice;
	
	
    /**
     * 24卖三量
     */
    @Column(name = "sale_three_count")
    private Integer saleThreeCount;
	
	
    /**
     * 25卖四价
     */
    @Column(name = "sale_four_price")
    private BigDecimal saleFourPrice;
	
	
    /**
     * 26卖四量
     */
    @Column(name = "sale_four_count")
    private Integer saleFourCount;
	
	
    /**
     * 27卖五价
     */
    @Column(name = "sale_five_price")
    private BigDecimal saleFivePrice;
	
	
    /**
     * 28卖五量
     */
    @Column(name = "sale_five_count")
    private Integer saleFiveCount;
	
	
    /**
     * 29最近逐笔成交
     */
    @Column(name = "lastest_deal")
    private BigDecimal lastestDeal;
	
	
    /**
     * 31涨跌
     */
    @Column(name = "up_or_down")
    private BigDecimal upOrDown;
	
	
    /**
     * 32涨跌幅
     */
    @Column(name = "quote_change")
    private BigDecimal quoteChange;
	
	
    /**
     * 33最高价
     */
    @Column(name = "highest_price")
    private BigDecimal highestPrice;
	
	
    /**
     * 34最低价
     */
    @Column(name = "lowest_price")
    private BigDecimal lowestPrice;
	
	
    /**
     * 35价格/成交量（手）/成交额
     */
    @Column(name = "price_info")
    private String priceInfo;
	
	
    /**
     * 38换手率
     */
    @Column(name = "turnover_rate")
    private BigDecimal turnoverRate;
	
	
    /**
     * 39市盈率
     */
    @Column(name = "pe_ratio")
    private BigDecimal peRatio;
	
	
    /**
     * 43振幅
     */
    @Column(name = "amplitude_")
    private BigDecimal amplitude;
	
	
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
     * 46市净率
     */
    @Column(name = "pb_ratio")
    private BigDecimal pbRatio;
	
	
    /**
     * 47涨停价
     */
    @Column(name = "high_limit_price")
    private BigDecimal highLimitPrice;
	
	
    /**
     * 48跌停价
     */
    @Column(name = "low_limit_price")
    private BigDecimal lowLimitPrice;
	
	
    /**
     * 49量比
     */
    @Column(name = "quantity_ratio")
    private BigDecimal quantityRatio;
	
	
    /**
     * 51均价
     */
    @Column(name = "average_price")
    private BigDecimal averagePrice;
	
	
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
     * 57成交额（万）
     */
    @Column(name = "volume_")
    private BigDecimal volume;
	
	
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
