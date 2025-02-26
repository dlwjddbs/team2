package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalesMapper {
	
	List<Map<String, Object>> selectOrder(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectOrderClient(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectOrderItem(Map<String, Object> requestData);

	int insertOrder(List<Map<String, Object>> createdRows);

	int updateOrder(List<Map<String, Object>> updatedRows);

	int selectTodayMaxOrderId();

	int deleteOrder(List<String> orderIds);
	
	int deleteShipmentRequest(List<String> orderIds);

	List<Map<String, Object>> selectRequestOrder(Map<String, Object> requestData);

	int insertRequestOrder(List<Map<String, Object>> createdRows);

	int selectTodayMaxShipmentRequestId();
	
	List<Map<String, Object>> selectShipmentRequest(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectShipmentRequestDetailLot(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectShipmentRequestDetail(Map<String, Object> requestData);

	int selectTodayMaxShipmentRequestDetailId();

	int insertShipmentRequestDetail(List<Map<String, Object>> createdRows);

	int updateShipmentRequestStatus(Map<String, Object> createdRow);

	int updateOrderStatus(Map<String, Object> createdRow);

	int updateWarehouseQuantity(List<Map<String, Object>> createdRows);

	int updateProductionLotQuantity(List<Map<String, Object>> createdRows);

}
