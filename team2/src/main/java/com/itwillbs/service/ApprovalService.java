package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.ApprovalMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalService {

	private final ApprovalMapper approvalMapper;

	public Map<String, Object> insertApprovalRequest(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 코드입니다.";
		String resultCode = "0";
		
		try {
			// 나중에 휴가 중복처리 해야할 듯...
//			int duplicateCnt = commonCodeMapper.isDuplicateCommonCodeGroup(map);
//			if (duplicateCnt == 0) {
//				int resultCnt = approvalMapper.insertApprovalRequest(map);
//				if (resultCnt > 0) {
//					result = "등록 되었습니다.";
//					resultCode = "1";
//				} else {
//					result = "등록 실패.";
//				}
//			}
		} catch (Exception e) {
			result = "등록 실패.";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}
	
//	public List<Map<String, Object>> getGroupCommonCode(Map<String, Object> map) {
//		return commonCodeMapper.getGroupCommonCode(map);
//	}
	
}
