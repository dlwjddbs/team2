package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.ManufactureMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManufactureService {
	
	private final ManufactureMapper manufactureMapper;
	
	public Map<String, Object> selectWorkcenter() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectWorkcenter 성공";
		
		try {
			List<Map<String, Object>> workcenterList = manufactureMapper.selectWorkcenter();
			content.put("contents", workcenterList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectWorkcenter 실패";
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
				manufactureMapper.insertWorkcenter(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				manufactureMapper.updateWorkcenter(updatedRows);
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
				manufactureMapper.deleteWorkcenter(idList);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteWorkcenter 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
}
