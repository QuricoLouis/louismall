package com.louisblogs.louismall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louisblogs.common.utils.PageUtils;
import com.louisblogs.common.utils.Query;

import com.louisblogs.louismall.product.dao.ProductAttrValueDao;
import com.louisblogs.louismall.product.entity.ProductAttrValueEntity;
import com.louisblogs.louismall.product.service.ProductAttrValueService;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public void saveProductAttr(List<ProductAttrValueEntity> collect) {
		this.saveBatch(collect);
	}

}