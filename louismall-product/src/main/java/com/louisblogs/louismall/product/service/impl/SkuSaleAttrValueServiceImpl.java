package com.louisblogs.louismall.product.service.impl;

import com.louisblogs.louismall.product.vo.SkuItemSaleAttrVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louisblogs.common.utils.PageUtils;
import com.louisblogs.common.utils.Query;

import com.louisblogs.louismall.product.dao.SkuSaleAttrValueDao;
import com.louisblogs.louismall.product.entity.SkuSaleAttrValueEntity;
import com.louisblogs.louismall.product.service.SkuSaleAttrValueService;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId) {
		SkuSaleAttrValueDao dao = this.baseMapper;
		List<SkuItemSaleAttrVo> saleAttrVos = dao.getSaleAttrsBySpuId(spuId);
		return saleAttrVos;
	}

	@Override
	public List<String> getSkuSaleAttrValuesAsStringList(Long skuId) {
		SkuSaleAttrValueDao dao = this.baseMapper;
		return dao.getSkuSaleAttrValuesAsStringList(skuId);
	}

}