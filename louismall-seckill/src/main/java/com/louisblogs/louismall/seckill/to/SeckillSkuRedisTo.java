package com.louisblogs.louismall.seckill.to;

import com.louisblogs.louismall.seckill.vo.vo.SkuInfoVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：luqi
 * @description：TODO
 * @date ：2021/7/1 20:44
 */

@Data
public class SeckillSkuRedisTo {

	/**
	 * 活动id
	 */
	private Long promotionId;
	/**
	 * 活动场次id
	 */
	private Long promotionSessionId;
	/**
	 * 商品id
	 */
	private Long skuId;
	/**
	 * 秒杀价格
	 */
	private BigDecimal seckillPrice;
	/**
	 * 秒杀总量
	 */
	private BigDecimal seckillCount;
	/**
	 * 每人限购数量
	 */
	private BigDecimal seckillLimit;
	/**
	 * 排序
	 */
	private Integer seckillSort;

	//sku详细信息
	private SkuInfoVo skuInfoVo;

	//当前sku的秒杀开始时间
	private Long startTime;

	//当前sku的秒杀结束时间
	private Long endTime;

	//秒杀随机码
	private String randomCode;

}