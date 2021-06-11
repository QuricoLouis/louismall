package com.louisblogs.louismall.ware.service.impl;

import com.louisblogs.common.to.SkuHasStockVo;
import com.louisblogs.common.utils.R;
import com.louisblogs.louismall.ware.feign.ProductFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louisblogs.common.utils.PageUtils;
import com.louisblogs.common.utils.Query;

import com.louisblogs.louismall.ware.dao.WareSkuDao;
import com.louisblogs.louismall.ware.entity.WareSkuEntity;
import com.louisblogs.louismall.ware.service.WareSkuService;
import org.springframework.util.StringUtils;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

	@Autowired
	WareSkuDao wareSkuDao;

	@Autowired
	ProductFeignService productFeignService;

	//查询商品库存
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		/**
		 * skuId: 1
		 * wareId: 2
		 */
		QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
		String skuId = (String) params.get("skuId");
		if (!StringUtils.isEmpty(skuId)) {
			queryWrapper.eq("sku_id", skuId);
		}

		String wareId = (String) params.get("wareId");
		if (!StringUtils.isEmpty(wareId)) {
			queryWrapper.eq("ware_id", wareId);
		}

		IPage<WareSkuEntity> page = this.page(
				new Query<WareSkuEntity>().getPage(params),
				queryWrapper
		);

		return new PageUtils(page);
	}

	@Override
	public void addStock(Long skuId, Long wareId, Integer skuNum) {

		//1、判断如果还没有这个库存记录新增
		List<WareSkuEntity> entities = wareSkuDao.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
		if (entities == null || entities.size() == 0) {
			WareSkuEntity skuEntity = new WareSkuEntity();
			skuEntity.setSkuId(skuId);
			skuEntity.setStock(skuNum);
			skuEntity.setWareId(wareId);
			skuEntity.setStockLocked(0);
			//TODO 远程查询sku的名字,如果失败整个事务无需回滚
			//1、自己catch异常
			//TODO 还可以用什么办法让异常出现以后不回滚？
			try {
				R info = productFeignService.info(skuId);
				Map<String, Object> data = (Map<String, Object>) info.get("skuinfo");
				if (info.getCode() == 0) {
					skuEntity.setSkuName((String) data.get("skuName"));
				}
			} catch (Exception e) {

			}

			wareSkuDao.insert(skuEntity);
		} else {
			wareSkuDao.addStock(skuId, wareId, skuNum);
		}
		wareSkuDao.addStock(skuId, wareId, skuNum);

	}

	@Override
	public List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds) {

		List<SkuHasStockVo> collect = skuIds.stream().map(skuId -> {
			SkuHasStockVo vo = new SkuHasStockVo();

			//查询当前sku的总库存量
			//SELECT SUM(stock-stock_locked) FROM `wms_ware_sku` WHERE sku_id=2
			Long count = baseMapper.getSkuStock(skuId);

			vo.setSkuId(skuId);
			vo.setHasStock(count==null?false:count>0);
			return vo;
		}).collect(Collectors.toList());
		return collect;
	}

}















