package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.OrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderMapper orderMapper;
	
	public Map<String, Object> getOrder() {
		
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "data get 성공";
		
		try {
			List<Map<String, Object>> orderList = orderMapper.getOrder();
			content.put("contents", orderList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			System.err.println("데이터 조회 실패" + e);
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
}
