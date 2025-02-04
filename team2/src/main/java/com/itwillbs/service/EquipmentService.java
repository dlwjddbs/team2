package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.EquipmentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class EquipmentService {
	
	private final EquipmentMapper equipmentMapper;
	
	public Map<String, Object> selectEquipment() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> equipmentList = equipmentMapper.selectEquipment();
			content.put("contents", equipmentList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectEquipment 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

}
