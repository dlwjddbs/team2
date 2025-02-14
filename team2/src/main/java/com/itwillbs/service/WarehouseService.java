package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.WarehouseMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class WarehouseService {

	private final WarehouseMapper warehouseMapper;

	public Map<String, Object> selectWarehouse() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectWarehouse 성공";
		
		try {
			List<Map<String, Object>> warehouseList = warehouseMapper.selectWarehouse();
			content.put("contents", warehouseList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectWarehouse 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyWarehouse(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		Map<String, Object> resultMap = new HashMap<>();
		
		log.info("requestData : " + requestData);
		log.info("updatedRows : " + updatedRows);
		log.info("createdRows : " + createdRows);
		
		Boolean result = true;
		String message = "modifyWarehouse 성공";
		
		try {
			if (createdRows.size() > 0) {
				warehouseMapper.insertWarehouse(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				warehouseMapper.updateWarehouse(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyWarehouse 실패";		    
					e.printStackTrace();  // 콘솔에 오류 출력 (로그 추가 가능)
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> deleteWarehouse(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteWarehouse 성공";
		
		try {
			if (idList.size() > 0) {
				warehouseMapper.deleteWarehouse(idList);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteWarehouse 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	// 창고 코드 중복검사
	public Map<String, Object> checkDuplicateWhseCode(Map<String, Object> map) {
		log.info("============= checkDuplicateWhseCode(Service) =============");
		Map<String, Object> message = new HashMap<>();
		
		String result = "중복된 코드입니다.";
		String resultCode = "0";
		
		try {
			int resultCnt = warehouseMapper.checkDuplicateWhseCode(map);
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
	
	// 위치 코드 중복검사
	public Map<String, Object> checkDuplicateLocationCode(Map<String, Object> map) {
		log.info("============= checkDuplicateLocationCode(Service) =============");
		Map<String, Object> message2 = new HashMap<>();
		
		String result = "중복된 코드입니다.";
		String resultCode = "0";
		
		try {
			int resultCnt = warehouseMapper.checkDuplicateLocationCode(map);
			if (resultCnt == 0) {
				result = "사용가능한 코드입니다.";
				resultCode = "1";
			}
		} catch (Exception e) {
			result = "조회 실패. 재시도 하세요.";
			resultCode = "0";
		}
		
		message2.put("result", result);
		message2.put("resultCode", resultCode);
		
		return message2;
	}

	public Map<String, Object> selectLocation(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectLocation 성공";
		
		try {
			List<Map<String, Object>> locationList = warehouseMapper.selectLocation(requestData);
			content.put("contents", locationList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectLocation 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

//	public Map<String, Object> addLocation(List<Map<String, Object>> createdRows) {
//		Map<String, Object> resultMap = new HashMap<>();
//		Boolean result = true;
//		String message = "addLocation 성공";
//		
//		try {
//			warehouseMapper.addLocation(createdRows);
//		} catch (Exception e) {
//			result = false;
//			message = "addLocation 실패";
//		}
//		
//		resultMap.put("result", result);
//		resultMap.put("message", message);
//		
//		return resultMap;
//	}
	
//	@Transactional(rollbackFor = Exception.class)
//	public Map<String, Object> addLocation(Map<String, Object> requestData) {
//		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
//
//		Map<String, Object> resultMap = new HashMap<>();
//		
//		Boolean result = true;
//		String message = "modifyToastTest 성공";
//		
//		try {
//			if (createdRows.size() > 0) {
//				warehouseMapper.insertLocation(createdRows);
//			}
//			
//		} catch (Exception e) {
//			result = false;
//			message = "modifyToastTest 실패";
//		}
//		
//		resultMap.put("result", result);
//		resultMap.put("message", message);
//		
//		return resultMap;
//	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> addLocation(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		Map<String, Object> resultMap = new HashMap<>();
		
		log.info("requestData : " + requestData);
		log.info("updatedRows : " + updatedRows);
		log.info("createdRows : " + createdRows);
		
		Boolean result = true;
		String message = "modifyitemLocation 성공";
		
		try {
			if (createdRows.size() > 0) {
				warehouseMapper.insertLocation(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				warehouseMapper.updateLocation(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyitemLocation 실패";		    
					e.printStackTrace();  // 콘솔에 오류 출력 (로그 추가 가능)
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}	
	
	public Map<String, Object> deleteLocation(List<String> locationIds) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteLocation 성공";
		
		try {
			if (locationIds.size() > 0) {
				warehouseMapper.deleteLocation(locationIds);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteLocation 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}	

}
