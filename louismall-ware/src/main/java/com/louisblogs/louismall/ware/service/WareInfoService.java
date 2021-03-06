package com.louisblogs.louismall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louisblogs.common.utils.PageUtils;
import com.louisblogs.louismall.ware.entity.WareInfoEntity;
import com.louisblogs.louismall.ware.vo.FareVo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 仓库信息
 *
 * @author luqi
 * @email lq844040753@163.com
 * @date 2021-05-17 17:29:31
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

	/**
	 * 根据送货地址计算运费
	 * @param addrId
	 * @return
	 */
	FareVo getFare(Long addrId);
}

