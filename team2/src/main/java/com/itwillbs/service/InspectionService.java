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
		
		String poDetailId = rejectionList.get(0).get("PODETAIL_ID").toString();
		
		try {
			System.out.println(poDetailId);
	        // 기존 데이터 삭제
	        inspectionMapper.deleteInboundInspection(poDetailId);

	        // 불량 수량이 0보다 큰 데이터만 필터링
	        List<Map<String, Object>> validRejectionList = rejectionList.stream()
	            .filter(item -> item.containsKey("DEFECT_QUANTITY") && 
	                            item.get("DEFECT_QUANTITY") != null && 
	                            Integer.parseInt(item.get("DEFECT_QUANTITY").toString()) > 0)
	            .collect(Collectors.toList());
	        
	        // 유효한 데이터가 있을 때만 insert 수행
	        if (!validRejectionList.isEmpty()) {
	            inspectionMapper.insertInboundInspection(validRejectionList);
	        }
        } catch (Exception e) {
        	
        	System.out.println("서비스 오류 e: " + e);
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

	public Map<String, Object> insertInboundLots(Map<String, Object> map) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "insertInboundLots 성공";
		
		try {
			inspectionMapper.insertInboundLots(map);
			inspectionMapper.updatePoDetailStatus(map);
			inspectionMapper.updatePurchaseOrderStatus(map);
		} catch (Exception e) {
			result = false;
			message = "insertInboundLots 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> selectDefectCode(Map<String, Object> map) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> rejectionList = inspectionMapper.selectDefectCode(map);
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

	public Map<String, Object> selectDefectCauseCode(Map<String, Object> map) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> rejectionList = inspectionMapper.selectDefectCauseCode(map);
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

	public Map<String, Object> insertProductionInspection(List<Map<String, Object>> list) {
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("insertProductionInspection");
		Boolean result = true;
		String message = "insertProductionInspection 성공";
		
		try {
			inspectionMapper.insertProductionInspection(list);
			inspectionMapper.insertProductionLot(list);
			inspectionMapper.updateProductionOrderDetail(list);
			inspectionMapper.updateWortcenterLog(list);
		} catch (Exception e) {
			System.out.println(e);
			result = false;
			message = "insertProductionInspection 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

}
