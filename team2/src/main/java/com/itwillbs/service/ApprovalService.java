package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.ApprovalMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalService {

	private final ApprovalMapper approvalMapper;

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> createApprovalRequest(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 코드입니다.";
		String resultCode = "0";
		
		try {
			// 1. Approval Request INSERT 및 REQUEST_ID 반환
			approvalMapper.insertApprovalRequest(map);
			String REQUEST_ID = map.get("request_id").toString();
			
			// 2. 결재 라인 SELECT
	        List<Map<String, Object>> approvalSteps = approvalMapper.selectApprovalLine(map.get("MEMBER_ID").toString());
	        
	        // 3. Approval Step INSERT 반복
	        for (Map<String, Object> step : approvalSteps) {
	        	Map<String, String> tmpMap = new HashMap<>();
	        	
	        	String STEP_ORDER = step.get("HIERARCHY_LEVEL").toString();
	        	// 첫번째 순서만 PENDING, 나머진 DEACTIVATION, PENDING이 결재 진행중 단계
	        	// DEACTIVATION, PENDING, APPROVED, REJECTED, PREV_STEP_REJECTED
	        	String STATUS = "DEACTIVATION";
	        	if (STEP_ORDER.equals("1")) {
	        		STATUS = "PENDING";
	        	}
	        	
	        	tmpMap.put("REQUEST_ID", REQUEST_ID);
	        	tmpMap.put("APPROVER_ID", step.get("APPROVER_ID").toString());
	        	tmpMap.put("STEP_ORDER", STEP_ORDER);
	        	tmpMap.put("STATUS", STATUS);
	        	
	        	int resultCnt = approvalMapper.insertApprovalStep(tmpMap);
	        }
	        
	        result = "등록 성공";
			resultCode = "1";
		} catch (Exception e) {
			result = "등록 실패.";
			throw e;
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}
	
}
