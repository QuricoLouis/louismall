package com.louisblogs.louismall.order.listener;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.louisblogs.louismall.order.config.AlipayTemplate;
import com.louisblogs.louismall.order.service.OrderService;
import com.louisblogs.louismall.order.vo.PayAsyncVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ：luqi
 * @description：TODO
 * @date ：2021/7/1 15:09
 */

@RestController
public class OrderPayedListener {

	@Autowired
	OrderService orderService;

	@Autowired
	AlipayTemplate alipayTemplate;

	/**
	 * 处理支付宝返回的数据
	 *
	 * 只要我们收到了，支付宝给我们的一步的通知，告诉我订单支付成功
	 * 返回success，支付宝就再也不通知
	 */
	@PostMapping("/payed/notify")
	public String handleAlipayed(PayAsyncVo payAsyncVo, HttpServletRequest request) throws AlipayApiException {

//        Map<String, String[]> map = request.getParameterMap();
//		  System.out.println("支付宝通知到位了...数据:"+map);
//        for (String key : map.keySet()) {
//            String value = request.getParameter(key);
//            System.out.println("参数名："+key + " -> 参数名：" + value);
//        }

		//防止别人伪造支付宝的数据

		//验签：是不是支付宝给我们返回的数据
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayTemplate.getAlipay_public_key(), alipayTemplate.getCharset(), alipayTemplate.getSign_type()); //调用SDK验证签名

		if (signVerified) {
			System.out.println("签名验证成功");
			String result = orderService.handlePayResult(payAsyncVo);
			return result;
		}else {
			System.out.println("签名验证失败");
			return "error";
		}
	}
}
