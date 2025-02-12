package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/saveGoods")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> saveGoods(@AuthenticationPrincipal User user, @RequestBody List<Map<String, Object>> dataList) {
	    int result = 0;
	    String id = user.getUsername();
	    
	    List<Map<String, Object>> insertList = new ArrayList<>();
	    List<Map<String, Object>> updateList = new ArrayList<>();
	    List<Map<String, Object>> deleteList = new ArrayList<>();

	    for (Map<String, Object> data : dataList) {
	    	data.put("INSERT_MEM", id);
	    	data.put("UPDATE_MEM", id);
	        String rowType = (String) data.get("rowType");
	        System.out.println("처리할 데이터: " + rowType + " → " + data); // rowType 출력

	        if ("insert".equals(rowType)) {
	            insertList.add(data);
	        } else if ("update".equals(rowType)) {
	            updateList.add(data);
	        } else if ("delete".equals(rowType)) {
	            deleteList.add(data);
	        }
	    }

	    result += goodsOrderService.insertGoods(insertList);
	    result += goodsOrderService.updateGoods(updateList);
	    result += goodsOrderService.deleteGoods(deleteList);
	    
	    System.out.println("최종 저장된 데이터 개수: " + result); // 처리된 개수 출력
	 // JSON 형태로 응답 반환 (undefined 방지)
	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("status", "success");
	    responseMap.put("message", "저장이 완료되었습니다!");
	    responseMap.put("affectedRows", result);
	    
	    return ResponseEntity.ok(responseMap);
	}
	
	@PostMapping(goodsPo)
	@ResponseBody
	public List<Map<String, Object>> getGoodsPoList(@RequestBody Map<String, Object> requestData ) {
		System.out.println("발주리스트" + requestData.toString());
		List<Map<String, Object>> getGoodsPoList = goodsOrderService.getGoodsPoList(requestData);
		return getGoodsPoList;
	}
	
	@PostMapping("getGoodsDetail")
	@ResponseBody
	public List<Map<String, Object>> getGoodsDetail(@RequestBody Map<String, Object> map) {
		List<Map<String, Object>> goodsDetail = goodsOrderService.getGoodsDetail(map);
		System.out.println("받은 리스트 :" + map.toString());
		System.out.println("goodsList: " + goodsDetail);
		return goodsDetail;
	}
	
}
