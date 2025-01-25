package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.TestMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	private final TestMapper testMapper;
	
	public String getTest() {
		return testMapper.selectTest();
	}
	
	public Map<String, Object> selectToastTest() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectToastTest 성공";
		
		try {
			List<Map<String, Object>> testList = testMapper.selectToastTest();
			content.put("contents", testList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> insertToastTest(List<Map<String, Object>> createdRows) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "insertToastTest 성공";
		
		try {
			testMapper.insertToastTest(createdRows);
		} catch (Exception e) {
			result = false;
			message = "insertToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
}
