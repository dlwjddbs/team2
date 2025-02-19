package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.InspectionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InspectionService {
	private final InspectionMapper inspectionMapper;
	

	public Map<String, Object> selectInboundList() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> rejectionList = inspectionMapper.selectInboundList();
			content.put("contents", rejectionList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectEquipment 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> selectInboundDetail(Map<String, Object> map) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> rejectionList = inspectionMapper.selectInboundDetail(map);
			content.put("contents", rejectionList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectEquipment 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> selectRejectionCode() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> rejectionList = inspectionMapper.selectRejectionCode();
			content.put("contents", rejectionList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectEquipment 실패";
		}
		
		System.out.println(resultMap);
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	public Map<String, Object> insertRejectionCode(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "insertToastTest 성공";
		
		try {
			int cnt = inspectionMapper.countExistingIds(createdRows);
			if (cnt > 0) {
				result = false;
				message = "중복된 ToastTest값";
			} else {
				inspectionMapper.insertRejectionCode(createdRows);
			}
		} catch (Exception e) {
			result = false;
			message = "insertToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> deleteRejectionCode(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteToastTest 성공";
		
		try {
			inspectionMapper.deleteRejectionCode(idList);
		} catch (Exception e) {
			result = false;
			message = "deleteToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> updateRejectionCode(List<Map<String, Object>> updatedRows) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "updateToastTest 성공";
		
		try {
			inspectionMapper.updateRejectionCode(updatedRows);
		} catch (Exception e) {
			result = false;
			message = "updateToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> processInboundInspection(List<Map<String, Object>> rejectionList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "processInboundInspection() 성공";
		
		try {
            String poDetailId = rejectionList.get(0).get("PODETAIL_ID").toString();
            inspectionMapper.mergeInboundInspection(rejectionList);
            inspectionMapper.deletemissingInboundInspection(poDetailId, rejectionList);
        } catch (Exception e) {
            result = false;
            message = "processInboundInspection() 실패";
        }

        resultMap.put("result", result);
        resultMap.put("message", message);
        
		return resultMap;
	}

	public Map<String, Object> selectInboundInspectionList(Map<String, Object> map) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> rejectionList = inspectionMapper.selectInboundInspectionList(map);
			content.put("contents", rejectionList);
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
