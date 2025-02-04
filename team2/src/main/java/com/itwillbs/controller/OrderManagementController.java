package com.itwillbs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderManagementController {
	
	private final OrderService orderService;

	private final String URL = "/ajax/order";

	@GetMapping("/order/orderManage")
	public String getOrderList() {
		return "orderManagement/orderManage";
	}
		
	@GetMapping(URL)
	@ResponseBody
	public Map<String, Object> getOrder() {
		
		Map<String, Object> orderData = orderService.getOrder();
		log.info("db값" + orderData);
		
		return orderData;
	}	
}
