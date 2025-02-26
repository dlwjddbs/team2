package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.InspectionMapper;
import com.itwillbs.repository.LotsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LotsService {
	
	private final LotsMapper lotsMapper;

	public Map<String, Object> selectLots() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectLots 성공";
		
		try {
			List<Map<String, Object>> lotList = lotsMapper.selectLots();
			System.out.println(lotList);
			content.put("contents", lotList);
			resultMap.put("data", content);
		} catch (Exception e) {
			System.out.println(e);
			result = false;
			message = "selectLots 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

}
