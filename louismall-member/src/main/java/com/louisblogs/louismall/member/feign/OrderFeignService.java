package com.louisblogs.louismall.member.feign;

import com.louisblogs.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author ：luqi
 * @description：TODO
 * @date ：2021/7/1 10:21
 */

@FeignClient("louismall-order")//这个远程服务
public interface OrderFeignService {

	/**
	 * 给远程服务使用的 我的订单
	 * 查询当前登录用户的所有订单详情数据（分页）
	 * @RequestBody 远程传输必须用这个
	 */
	@PostMapping("/order/order/listWithItem")
	R listWithItem(@RequestBody Map<String, Object> params);
}
