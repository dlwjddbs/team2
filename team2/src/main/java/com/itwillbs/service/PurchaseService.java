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

	public int insertPurchases(List<Map<String, Object>> insertList) {
		int count = 0;
        for (Map<String, Object> data : insertList) {
            count += purchaseMapper.insertPurchase(data);
        }
        return count;
	}

	public int updatePurchases(List<Map<String, Object>> updateList) {
		 int count = 0;
	        for (Map<String, Object> data : updateList) {
	            count += purchaseMapper.updatePurchase(data);
	        }
	        return count;
	}

	public int deletePurchases(List<Map<String, Object>> deleteList) {
		 int count = 0;
	        for (Map<String, Object> data : deleteList) {
	            count += purchaseMapper.deletePurchase(data);
	        }
	        return count;
	}
	
	
}
