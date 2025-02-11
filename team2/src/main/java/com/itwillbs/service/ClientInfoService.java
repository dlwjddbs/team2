package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyClientInfo(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyClientInfo 성공";
		
		try {
			if (createdRows.size() > 0) {
				clientInfoMapper.insertClientInfo(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				clientInfoMapper.updateClientInfo(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> deleteClientInfo(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteClientInfo 성공";
		
		try {
			if (idList.size() > 0) {
				clientInfoMapper.deleteClientInfo(idList);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteClientInfo 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> checkDuplicateClientCode(Map<String, Object> map) {
		log.info("============= checkDuplicateClientCode(Service) =============");
		Map<String, Object> message = new HashMap<>();
		
		String result = "중복된 코드입니다.";
		String resultCode = "0";
		
		try {
			int resultCnt = clientInfoMapper.checkDuplicateClientCode(map);
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
