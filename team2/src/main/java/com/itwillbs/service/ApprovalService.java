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
			String MEMBER_ID = map.get("MEMBER_ID").toString();
	        List<Map<String, Object>> approvalSteps = approvalMapper.selectApprovalLine(MEMBER_ID);
	        
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
	        
	        // 4. 자신이 결재권자일 경우 자동 승인
	        if (approvalSteps.size() != 0) {
	        	if (MEMBER_ID.equals(approvalSteps.get(0).get("APPROVER_ID").toString())) {
	        		Map<String, Object> tmpMap = new HashMap<>();
	        		tmpMap.put("REQUEST_ID", REQUEST_ID);
		        	tmpMap.put("APPROVER_ID", MEMBER_ID);
		        	tmpMap.put("STATUS", "APPROVED");
	        		
	        		// 1. 현재 단계 승인처리
	    			approvalMapper.updateApprovalRequest(tmpMap);
	    			
	    			// 2. 다음단계 대기 처리
	    			approvalMapper.pendingApprovalRequest(tmpMap);
	        	}
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

	public List<Map<String, Object>> selectApprovalPendingList(Map<String, Object> map) {
		return approvalMapper.selectApprovalPendingList(map);
	}

	public List<Map<String, Object>> approvalRequestDetail(Map<String, Object> map) {
		return approvalMapper.approvalRequestDetail(map);
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> approveApprovalRequest(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "승인 실패.";
		String resultCode = "0";
		
		try {
			// 1. 현재 단계 승인처리
			approvalMapper.updateApprovalRequest(map);
			
			// 2. 다음단계 대기 처리
			approvalMapper.pendingApprovalRequest(map);
			
			result = "승인 되었습니다.";
			resultCode = "1";
		} catch (Exception e) {
			throw e;
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> returnApprovalRequest(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
	
		String result = "반려 실패.";
		String resultCode = "0";
		
		try {
			// 1. 현재 단계 반려처리
			approvalMapper.updateApprovalRequest(map);
			
			// 2. 다음의 모든 단계 PREV_STEP_REJECTED 처리
			approvalMapper.psRejectedApprovalRequest(map);
			
			result = "반려 되었습니다.";
			resultCode = "1";
		} catch (Exception e) {
			throw e;
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	public Map<String, Object> getApprovalRequestStandbyMinMaxDate(Map<String, Object> map) {
		return approvalMapper.getApprovalRequestStandbyMinMaxDate(map);
	}

	public Map<String, Object> cancelApprovalRequest(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "취소 실패.";
		String resultCode = "0";
		
		try {
			// 미처리된 모든 단계 취소 처리
			int resultCnt = approvalMapper.cancelApprovalRequest(map);
			if (resultCnt > 0) {
				result = "취소 되었습니다.";
				resultCode = "1";
			}
		} catch (Exception e) {
			throw e;
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	public Map<String, Object> getApprovalRequestCompletionMinMaxDate(Map<String, Object> map) {
		return approvalMapper.getApprovalRequestCompletionMinMaxDate(map);
	}

	public List<Map<String, Object>> selectApprovalCompletionList(Map<String, Object> map) {
		return approvalMapper.selectApprovalCompletionList(map);
	}
	
}
