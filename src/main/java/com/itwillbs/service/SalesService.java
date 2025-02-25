package com.itwillbs.service;


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
	
}
