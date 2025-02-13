package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.OrderMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderMapper orderMapper;

    // 수주 조회 (orderId가 있으면 상세 조회, 없으면 전체 조회, 필터링 조건)
    public Map<String, Object> getFilteredOrders(Map<String, Object> filters) {
        // 필터 조건에 맞는 수주 데이터를 조회
        List<Map<String, Object>> orderData = orderMapper.selectFilteredOrders(filters);
        
        // 결과를 response에 담아 반환
        Map<String, Object> response = new HashMap<>();
        response.put("orders", orderData);
        return response;
    }

	public void insertOrder(Map<String, Object> orderData) {
		orderMapper.insertOrder(orderData);
	}
	
	public void updateOrder(Map<String, Object> orderData) {
		orderMapper.updateOrder(orderData);
	}
	
    public void deleteOrder(List<String> orderIds) {
        orderMapper.deleteOrder(orderIds);
    }


	public List<Map<String, Object>> getItemList(Map<String, Object> map) {
		return orderMapper.getItemList(map);
	}

	public Map<String, Object> getClientList(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectClient 성공";
		
		try {
			List<Map<String, Object>> purchaseList = orderMapper.getClientList(requestData);
			content.put("contents", purchaseList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "getClientList 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

}
