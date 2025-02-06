package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.PurchaseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	
	private final PurchaseMapper purchaseMapper;

	public List<Map<String, Object>> getPurchaseList(Map<String, Object> map) {
		return purchaseMapper.getPurchaseList(map);
	}

	public int insertPurchase(List<Map<String, Object>> insertList) {
		int count = 0;
        for (Map<String, Object> data : insertList) {
            count += purchaseMapper.insertPurchase(data);
        }
        return count;
	}

	public int updatePurchase(List<Map<String, Object>> updateList) {
		 int count = 0;
		 
	        for (Map<String, Object> data : updateList) {
	            count += purchaseMapper.updatePurchase(data);
	        }
	        return count;
	}

	public int deletePurchase(List<Map<String, Object>> deleteList) {
		 int count = 0;
	        for (Map<String, Object> data : deleteList) {
	            count += purchaseMapper.deletePurchase(data);
	        }
	        return count;
	}	

	public List<Map<String, Object>> getPurchaseDetail(Map<String, Object> map) {
		return purchaseMapper.getPurchaseDetail(map);
	}

	public int insertDetail(List<Map<String, Object>> insertList) {
		int count = 0;
        for (Map<String, Object> data : insertList) {
            count += purchaseMapper.insertDetail(data);
        }
        return count;
	}

	public int updateDetail(List<Map<String, Object>> updateList) {
		 int count = 0;
		 
	        for (Map<String, Object> data : updateList) {
	            count += purchaseMapper.updateDetail(data);
	        }
	        return count;
	}

	public int deleteDetail(List<Map<String, Object>> deleteList) {
		int count = 0;
        for (Map<String, Object> data : deleteList) {
            count += purchaseMapper.deleteDetail(data);
        }
        return count;
}

	public List<Map<String, Object>> getMaterialList(Map<String, Object> map) {
		return purchaseMapper.getMaterialList(map);
	}

	public Map<String, Object> getClientList(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectMember 성공";
		
		try {
			List<Map<String, Object>> purchaseList = purchaseMapper.getClientList(requestData);
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
