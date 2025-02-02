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

	public List<Map<String, Object>> getPurchase(Map<String, Object> map) {
		return purchaseMapper.getPurchase(map);
	}
	
	
}
