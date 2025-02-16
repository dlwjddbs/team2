package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.BomMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class BomService {
	
	private final BomMapper bomMapper;

	public Map<String, Object> selectBom() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectBom 성공";
		
		try {
			List<Map<String, Object>> bomList = bomMapper.selectBom();
			content.put("contents", bomList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectBom 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyBom(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyBom 성공";
		
		try {
			if (createdRows.size() > 0) {
				bomMapper.insertBom(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				bomMapper.updateBom(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyBom 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteBom(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteBom 성공";
		
		try {
			if (idList.size() > 0) {
				bomMapper.deleteBom(idList);
				bomMapper.deleteBomDetailById(idList);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteBom 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	public Map<String, Object> selectBomDetail(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectBomDetail 성공";
		
		try {
			List<Map<String, Object>> bomDetailList = bomMapper.selectBomDetail(requestData);
			content.put("contents", bomDetailList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectBomDetail 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	public Map<String, Object> selectItemAndMaterial(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectItemInfo 성공";
		
		try {
			List<Map<String, Object>> ItemInfoList = bomMapper.selectItemAndMaterial(requestData);
			content.put("contents", ItemInfoList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectItemInfo 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> addBomDetail(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		Boolean result = true;
		String message = "addBomDetail 성공";
		
		log.info(createdRows.toString());
		
		try {
			if (createdRows.size() > 0) {
				bomMapper.addBomDetail(createdRows);
			
				Map<String, Object> map = createdRows.get(0);
				map.put("createdRowsCnt", createdRows.size());
				
				bomMapper.updateBomQuantity(map);
			}
			
		} catch (Exception e) {
			result = false;
			message = "addBomDetail 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteBomDetail(List<String> bomDetailIds) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteBomDetail 성공";
		
		try {
			if (bomDetailIds.size() > 0) {
				bomMapper.deleteBomDetail(bomDetailIds);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteBomDetail 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
}
