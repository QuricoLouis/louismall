package com.louisblogs.louismall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 秒杀活动场次
 * 
 * @author luqi
 * @email lq844040753@163.com
 * @date 2021-05-17 10:43:23
 */
@Data
@TableName("sms_seckill_session")
public class SeckillSessionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 场次名称
	 */
	private String name;
	/**
	 * 每日开始时间
	 */
	private Date startTime;
	/**
	 * 每日结束时间
	 */
	private Date endTime;
	/**
	 * 启用状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 自己后来添加的属性
	 * @TableField(exist = false) 不是数据库字段
	 */
	@TableField(exist = false)
	private List<SeckillSkuRelationEntity> seckillSkuRelationEntities;

}
