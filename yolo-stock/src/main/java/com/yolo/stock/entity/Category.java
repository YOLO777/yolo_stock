package com.yolo.stock.entity;

import com.yolo.stock.common.util.excel.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 门类表
 * 
 * @author zlj
 * @date 2021-03-09 15:54:09
 */
@Data
@Table(name = "category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 
     */
    @Id
    private Integer id;
	
	
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
	
	
    /**
     * 门类
     */
    @Column(name = "category_name")
    private String categoryName;
	
	
    /**
     * 门类代码
     */
    @Column(name = "category_code")
    private String categoryCode;

    @Transient
    @ExcelField(title = "门类名称及代码", align = 1, sort = 1)
    private String categoryNameAndCode;
	
}
