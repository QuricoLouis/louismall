package com.louisblogs.louismall.search.controller;

import com.louisblogs.common.exception.BizCodeEnume;
import com.louisblogs.common.to.es.SkuEsModel;
import com.louisblogs.common.utils.R;
import com.louisblogs.louismall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：luqi
 * @description：TODO
 * @date ：2021/6/10 11:26
 */

@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {

	@Autowired
	ProductSaveService productSaveService;

	//上架商品
	@PostMapping("/product")
	public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {

		boolean b = false;
		try {
			b = productSaveService.productStatusUp(skuEsModels);
		} catch (Exception e) {
			log.error("ElasticSaveController商品上架错误: {}", e);
			return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
		}

		if (!b) {
			return R.ok();
		} else {
			return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
		}
	}

}
