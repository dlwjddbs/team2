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

    public void deleteOrder(List<String> orderIds) {
        orderMapper.deleteOrder(orderIds);
    }

	public void updateOrder(Map<String, Object> orderData) {
		orderMapper.updateOrder(orderData);
	}

	public List<Map<String, Object>> getItemList(Map<String, Object> map) {
		return orderMapper.getItemList(map);
	}
}
