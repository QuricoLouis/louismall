package com.louisblogs.louismall.ware.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.louisblogs.louismall.ware.vo.MergeVo;
import com.louisblogs.louismall.ware.vo.PurshaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.louisblogs.louismall.ware.entity.PurchaseEntity;
import com.louisblogs.louismall.ware.service.PurchaseService;
import com.louisblogs.common.utils.PageUtils;
import com.louisblogs.common.utils.R;


/**
 * 采购信息
 *
 * @author luqi
 * @email lq844040753@163.com
 * @date 2021-05-17 17:29:32
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
	@Autowired
	private PurchaseService purchaseService;

	///ware/purchase/done

	/**
	 * 完成采购
	 */
	@PostMapping("/done")
	public R finish(@RequestBody PurshaseDoneVo doneVo) {

		purchaseService.done(doneVo);

		return R.ok();

	}

	///ware/purchase/received

	/**
	 * 领取采购单
	 */
	@PostMapping("/received")
	public R merge(@RequestBody List<Long> ids) {

		purchaseService.received(ids);

		return R.ok();
	}

	///ware/purchase/merge

	/**
	 * 合并采购需求
	 */
	@PostMapping("/merge")
	public R merge(@RequestBody MergeVo mergeVo) {
		purchaseService.mergePurchase(mergeVo);

		return R.ok();
	}

	///ware/purchase/unreceive/list

	/**
	 * 查询未领取的采购单
	 */
	@RequestMapping("/unreceive/list")
	//@RequiresPermissions("ware:purchase:list")
	public R unreceivelist(@RequestParam Map<String, Object> params) {
		PageUtils page = purchaseService.queryPageUnreceive(params);

		return R.ok().put("page", page);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	//@RequiresPermissions("ware:purchase:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = purchaseService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("ware:purchase:info")
	public R info(@PathVariable("id") Long id) {
		PurchaseEntity purchase = purchaseService.getById(id);

		return R.ok().put("purchase", purchase);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	//@RequiresPermissions("ware:purchase:save")
	public R save(@RequestBody PurchaseEntity purchase) {
		purchase.setUpdateTime(new Date());
		purchase.setCreateTime(new Date());
		purchaseService.save(purchase);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	//@RequiresPermissions("ware:purchase:update")
	public R update(@RequestBody PurchaseEntity purchase) {
		purchaseService.updateById(purchase);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("ware:purchase:delete")
	public R delete(@RequestBody Long[] ids) {
		purchaseService.removeByIds(Arrays.asList(ids));

		return R.ok();
	}

}
