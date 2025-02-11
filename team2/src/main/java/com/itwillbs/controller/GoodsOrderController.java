package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.GoodsOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
@Controller
@RequiredArgsConstructor
@Log
public class GoodsOrderController {
	
	private final GoodsOrderService goodsOrderService;
	
	private final String goodsPo = "/goods/goodsPo";
	
	@GetMapping("/goodsOrder")
	public String purchase(@AuthenticationPrincipal User user) {
//		if (session.getAttribute("id") == null) {
//      return "redirect:/login"; 
//  }
		return "goodsOrder/goodsOrder";
	}
		
	@PostMapping("getGoods")
	@ResponseBody
	public List<Map<String, Object>> getGoodsList(@RequestBody Map<String, Object> map) {
		List<Map<String, Object>> goodsList = goodsOrderService.getGoodsList(map);
		System.out.println("받은 리스트 :" + map.toString());
		System.out.println("goodsList: " + goodsList);
		return goodsList;
	}
	
	@PostMapping(goodsPo)
	@ResponseBody
	public List<Map<String, Object>> getGoodsPoList(@RequestBody Map<String, Object> requestData ) {
		System.out.println("발주리스트" + requestData.toString());
		List<Map<String, Object>> getGoodsPoList = goodsOrderService.getGoodsPoList(requestData);
		return getGoodsPoList;
	}
	
	
	
}
