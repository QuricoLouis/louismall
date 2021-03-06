package com.louisblogs.louismall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louisblogs.common.utils.PageUtils;
import com.louisblogs.louismall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author luqi
 * @email lq844040753@163.com
 * @date 2021-05-17 10:44:59
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void saveProductAttr(List<ProductAttrValueEntity> collect);

	List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);

	void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);

}

