package com.louisblogs.louismall.order.web;

import com.louisblogs.common.exception.NoStockException;
import com.louisblogs.louismall.order.service.OrderService;
import com.louisblogs.louismall.order.vo.OrderConfirmVo;
import com.louisblogs.louismall.order.vo.OrderSubmitVo;
import com.louisblogs.louismall.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author ：luqi
 * @description：TODO
 * @date ：2021/6/26 17:00
 */

@Controller
public class OrderWebController {

	@Autowired
	OrderService orderService;

	/**
	 * 去结算
	 * 给订单确认ye返回数据
	 */
	@GetMapping("/toTrade")
	public String toTrade(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
		OrderConfirmVo confirmVo = orderService.confirmOrder();
//		map.put("orderConfirmData", confirmVo);
		model.addAttribute("orderConfirmData", confirmVo);
		//展示订单确认页 数据
		return "confirm";
	}

	/**
	 * 提交下订单 去支付
	 */
	@PostMapping("/submitOrder")
	public String submitOrder(OrderSubmitVo vo, Map<String, SubmitOrderResponseVo> map, RedirectAttributes redirectAttributes) {

		try {
			SubmitOrderResponseVo responseVo = orderService.submitOrder(vo);

			if (responseVo.getCode() == 0) {
				//说明下单成功 来到支付选择页
				map.put("submitOrderResp", responseVo);
				return "pay";
			}else {
				//说明下单失败 返回确认页 回到订单确认页重新提交订单信息
				String msg = "下单失败:";
				switch (responseVo.getCode()) {
					case 1:msg += "1订单信息过期，请刷新后提交"; break;
					case 2:msg += "2订单商品价格发生变化，请确认后再次提交"; break;
					case 3:msg += "3库存锁定失败，商品库存不足"; break;
				}
				redirectAttributes.addFlashAttribute("msg", msg);
				return "redirect:http://order.louismall.com/toTrade";
			}
		} catch (Exception e) {
			if (e instanceof NoStockException) {
				String message = ((NoStockException) e).getMessage();
				redirectAttributes.addFlashAttribute("msg", message);
			}
			return "redirect:http://order.louismall.com/toTrade";
		}
	}

}
