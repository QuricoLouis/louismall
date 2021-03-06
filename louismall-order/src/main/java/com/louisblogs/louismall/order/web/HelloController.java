package com.louisblogs.louismall.order.web;

import com.louisblogs.louismall.order.entity.OrderEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

/**
 * @author ：luqi
 * @description：TODO
 * @date ：2021/6/26 16:01
 */

@Controller
public class HelloController {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@ResponseBody
	@GetMapping("/test/creatPOrder")
	public String creatOrderTest() {
		OrderEntity entity = new OrderEntity();
		entity.setOrderSn("10010");
//		entity.setOrderSn(UUID.randomUUID().toString());
		entity.setModifyTime(new Date());

		//给MQ发消息
		rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", entity);
		return "ok";
	}

	@GetMapping("/{page}.html")
	public String listPage(@PathVariable("page") String page) {
		return page;
	}

}
