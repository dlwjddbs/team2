package com.itwillbs.service;

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
	
	
}
