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
    
	// 수주 등록
	void insertOrder(Map<String, Object> orderData);

	// 수주 수정
	void updateOrder(Map<String, Object> orderData);

	// 수주 삭제
    void deleteOrder(List<String> orderIds);

    // 품목 조회
	List<Map<String, Object>> getItemList(Map<String, Object> map);

	// 거래처 조회
	List<Map<String, Object>> getClientList(Map<String, Object> requestData);

}
