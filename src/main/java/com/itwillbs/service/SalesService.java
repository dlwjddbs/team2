package com.itwillbs.service;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.SalesMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesService {
	
	private final SalesMapper salesMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyOrder(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyOrder 성공";
		
		try {
			if (createdRows.size() > 0) {
	 			int max_id = salesMapper.selectTodayMaxOrderId();

	 			LocalDate now = LocalDate.now();
	 			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	 			String formatedNow = now.format(formatter);
	 			
	 			for (Map<String, Object> row : createdRows) {
	 				String orderId = "ORD-" + formatedNow + "-" + String.format("%04d", max_id);
	 				row.replace("ORDER_ID", orderId);
	 				
	 				max_id++;
	 			}
	 			
				salesMapper.insertOrder(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				salesMapper.updateOrder(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyOrder 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteOrder(List<String> orderIds) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteOrder 성공";
		
		try {
			if (orderIds.size() > 0) {
				salesMapper.deleteOrder(orderIds);
				salesMapper.deleteShipmentRequest(orderIds);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteOrder 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertRequestOrder(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		Boolean result = true;
		String message = "insertRequestOrder 성공";
		
		try {
			if (createdRows.size() > 0) {
				int max_id = salesMapper.selectTodayMaxShipmentRequestId();
				
				LocalDate now = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				String formatedNow = now.format(formatter);
				
				for (Map<String, Object> row : createdRows) {
					String shipmentRequestId = "SRI-" + formatedNow + "-" + String.format("%04d", max_id);
					row.put("SHIPMENT_REQUEST_ID", shipmentRequestId);
					
					max_id++;
				}
				
				salesMapper.insertRequestOrder(createdRows);
			}
		} catch (Exception e) {
			result = false;
			message = "insertRequestOrder 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertShipmentRequestDetail(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		Boolean result = true;
		String message = "insertShipmentRequestDetail 성공";
		
		try {
			if (createdRows.size() > 0) {
				int max_id = salesMapper.selectTodayMaxShipmentRequestDetailId();
				
				LocalDate now = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				String formatedNow = now.format(formatter);
				
				for (Map<String, Object> row : createdRows) {
					String shipmentRequestDetailId = "SRDI-" + formatedNow + "-" + String.format("%04d", max_id);
					row.put("SHIPMENT_REQUEST_DETAIL_ID", shipmentRequestDetailId);
					
					max_id++;
				}
				
				salesMapper.insertShipmentRequestDetail(createdRows);
				
				// 창고 개수 차감
				salesMapper.updateWarehouseQuantity(createdRows);
				// 로트 개수 차감
				salesMapper.updateProductionLotQuantity(createdRows);
				
				Map<String, Object> createdRow = createdRows.getFirst();
				// 출하 상태, 일자 갱신
				salesMapper.updateShipmentRequestStatus(createdRow);
				// 수주 상태, 일자 갱신
				salesMapper.updateOrderStatus(createdRow);
			}
		} catch (Exception e) {
			result = false;
			message = "insertShipmentRequestDetail 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	public Map<String, Object> selectShipmentRequestDetailLot(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		boolean result = true;
		String message = "selectShipmentRequestDetailLot 성공";
		
		try {
			List<Map<String, Object>> dataList = salesMapper.selectShipmentRequestDetailLot(requestData);
			
			Object requestQuantityObj = requestData.get("request_quantity");
			int remainingQuantity = getSafeIntValue(requestData.get("request_quantity"));

			for (Map<String, Object> data : dataList) {
				int currentQuantity = getSafeIntValue(data.get("CURRENT_QUANTITY"));
				
				int shippedQuantity = 0;
				
				if (remainingQuantity > 0) {
					if (currentQuantity <= remainingQuantity) {
						shippedQuantity = currentQuantity;
						remainingQuantity -= currentQuantity;
					} else {
						shippedQuantity = remainingQuantity;
						remainingQuantity = 0;
					}
				}
				
				data.put("SHIPPED_QUANTITY", shippedQuantity);
				data.put("SHIPPED_FLAG", shippedQuantity > 0 ? "Y" : "N");
			}
			
			content.put("contents", dataList);
			resultMap.put("data", content);
		} catch (Exception e) {
			System.out.println(e);
			result = false;
			message = "selectShipmentRequestDetailLot 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	public static int getSafeIntValue(Object obj) {
		if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).intValue();
		}
		
		if (obj instanceof Number) {
			return ((Number) obj).intValue();
		} 
		
		if (obj instanceof String) {
			return Integer.parseInt((String) obj);
		}
		
		return 0; // 변환 불가능한 경우 기본값 0
	}
	
}
