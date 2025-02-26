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
			content.put("contents", lotList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectLots 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public List<Map<String, Object>> selectLotHierarchy(Map<String, Object> map) {
		return lotsMapper.selectLotHierarchy(map);
	}

	public Map<String, Object> selectLotDetailInfo(Map<String, Object> map) {
		List<Map<String, Object>> lotDetail = lotsMapper.selectLotDetailInfo(map);
		List<Map<String, Object>> materialInfo = lotsMapper.selectMaterialInfo(map);
		Map<String, Object> data = new HashMap();
		data.put("lotDetail", lotDetail);
		data.put("materialInfo", materialInfo);
		
		return data;
	}

	public Map<String, Object> selectInboundInspection(Map<String, Object> map) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		System.out.println(map);
		
		Boolean result = true;
		String message = "selectLots 성공";
		
		try {
			List<Map<String, Object>> lotList = lotsMapper.selectInboundInspection(map);
			content.put("contents", lotList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectLots 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

}
