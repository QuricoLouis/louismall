package com.louisblogs.louismall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.louisblogs.common.to.SkuHasStockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.louisblogs.louismall.ware.entity.WareSkuEntity;
import com.louisblogs.louismall.ware.service.WareSkuService;
import com.louisblogs.common.utils.PageUtils;
import com.louisblogs.common.utils.R;


/**
 * 商品库存
 *
 * @author luqi
 * @email lq844040753@163.com
 * @date 2021-05-17 17:29:31
 */
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {

	@Autowired
	private WareSkuService wareSkuService;

	/**
	 * 查询sku是否有库存
	 */
	@PostMapping("/hasStock")
	public R getSkuHasStock(@RequestBody List<Long> skuIds) {

		//sku_id, stock
		List<SkuHasStockVo> vos = wareSkuService.getSkuHasStock(skuIds);

		return R.ok().setData(vos);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	//@RequiresPermissions("ware:waresku:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = wareSkuService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("ware:waresku:info")
	public R info(@PathVariable("id") Long id) {
		WareSkuEntity wareSku = wareSkuService.getById(id);

		return R.ok().put("wareSku", wareSku);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	//@RequiresPermissions("ware:waresku:save")
	public R save(@RequestBody WareSkuEntity wareSku) {
		wareSkuService.save(wareSku);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	//@RequiresPermissions("ware:waresku:update")
	public R update(@RequestBody WareSkuEntity wareSku) {
		wareSkuService.updateById(wareSku);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("ware:waresku:delete")
	public R delete(@RequestBody Long[] ids) {
		wareSkuService.removeByIds(Arrays.asList(ids));

		return R.ok();
	}

}
