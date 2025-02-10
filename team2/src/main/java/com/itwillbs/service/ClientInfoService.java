package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.ClientInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class ClientInfoService {
	
	private final ClientInfoMapper clientInfoMapper;
	
	public Map<String, Object> selectClientInfo() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectClientInfo 성공";
		
		try {
			List<Map<String, Object>> clientInfoList = clientInfoMapper.selectClientInfo();
			content.put("contents", clientInfoList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectClientInfo 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

}
