package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    // 전체 수주 목록 조회
    List<Map<String, Object>> getOrder();

    // 수주 상세 정보 조회
    List<Map<String, Object>> getOrder(@Param("orderId") String orderId);
    
    // 필터링된 수주 조회
	List<Map<String, Object>> selectFilteredOrders(Map<String, Object> filters);
    
    // 수주 삭제
    void deleteOrder(List<String> orderIds);

	void updateOrder(Map<String, Object> orderData);

	List<Map<String, Object>> getItemList(Map<String, Object> map);

	List<Map<String, Object>> getClientList(Map<String, Object> requestData);
}
