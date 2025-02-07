package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.ItemInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class ItemInfoService {
	
	private final ItemInfoMapper itemInfoMapper;
	
	public Map<String, Object> selectItemInfo() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectItemInfo 성공";
		
		try {
			List<Map<String, Object>> itemInfoList = itemInfoMapper.selectItemInfo();
			content.put("contents", itemInfoList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectItemInfo 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> checkDuplicateItemCode(Map<String, Object> map) {
		log.info("============= checkDuplicateItemCode(Service) =============");
		Map<String, Object> message = new HashMap<>();
		
		String result = "중복된 코드입니다.";
		String resultCode = "0";
		
		try {
			int resultCnt = itemInfoMapper.checkDuplicateItemCode(map);
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
