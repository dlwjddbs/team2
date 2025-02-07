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

    // 수주 조회 (orderId가 있으면 상세 조회, 없으면 전체 조회)
    public Map<String, Object> getOrder(String orderId) {
        List<Map<String, Object>> orderData = orderMapper.getOrder(orderId);  // orderId가 있으면 상세 조회, 없으면 전체 조회
        
        Map<String, Object> response = new HashMap<>();
        response.put("orders", orderData);
        return response;
    }

    public void deleteOrder(List<String> orderIds) {
        orderMapper.deleteOrder(orderIds);
    }
}
