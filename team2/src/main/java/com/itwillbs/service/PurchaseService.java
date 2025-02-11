package com.itwillbs.service;

import java.util.ArrayList;
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
	
	
	public int updateDetailAndStatus(List<Map<String, Object>> updateList) {
        int count = 0;
        // 1. PO_DETAIL 업데이트
        for (Map<String, Object> data : updateList) {
            count += purchaseMapper.updateDetail(data);
        }
        
        // 2. 해당 PO_ID의 PURCHASE_ORDER의 PO_STATUS 업데이트
        // updateList에서 PO_ID는 동일하다고 가정
        if (!updateList.isEmpty()) {
            Map<String, Object> data = updateList.get(0);
            // PO_ID 값을 추출해서 params에 담습니다.
            String poId = (String)data.get("poId");
            Map<String, Object> params = new HashMap<>();
            params.put("PO_ID", poId);
            
            purchaseMapper.updatePurchaseStatus(params);
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


	public Map<String, Object> getClientList(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectClient 성공";
		
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
	
	

	public List<Map<String, Object>> getMaterialList(Map<String, Object> requestData) {
	
		List<Map<String, Object>> materialList = purchaseMapper.getMaterialList(requestData);
		
		
		return materialList;
	}
	
	
}
