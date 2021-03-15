package com.yolo.stock.entity;

import com.yolo.stock.common.util.excel.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 行业大类表
 * 
 * @author zlj
 * @date 2021-03-09 15:54:09
 */
@Data
@Table(name = "industry")
public class Industry implements Serializable {
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
     * 行业大类
     */
    @Column(name = "ind_name")
    private String indName;
	
	
    /**
     * 行业代码
     */
    @Column(name = "ind_code")
    private String indCode;

    @Column(name="cate_id")
    private Integer cateId;
}
