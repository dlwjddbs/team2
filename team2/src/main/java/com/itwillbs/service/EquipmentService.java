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
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyWorkcenter(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyToastTest 성공";
		
		try {
			if (createdRows.size() > 0) {
				equipmentMapper.insertEquipment(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				equipmentMapper.updateEquipment(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> deleteWorkcenter(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteWorkcenter 성공";
		
		try {
			if (idList.size() > 0) {
				equipmentMapper.deleteEquipment(idList);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteWorkcenter 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> checkDuplicateEquipCode(Map<String, Object> map) {
		log.info("============= checkDuplicateEquipCode(Service) =============");
		Map<String, Object> message = new HashMap<>();
		
		String result = "중복된 코드입니다.";
		String resultCode = "0";
		
		try {
			int resultCnt = equipmentMapper.checkDuplicateEquipCode(map);
			if (resultCnt == 0) {
				result = "사용가능한 코드입니다.";
				resultCode = "1";
			}
		} catch (Exception e) {
			result = "조회 실패. 재시도 하세요.";
			resultCode = "0";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

}
