package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.ApprovalService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ApprovalController {
	
	private final ApprovalService approvalService;
	
//	@GetMapping("/commonCode")
//	public String commonCode(HttpSession session) {
//		
//		return "/commonCode/common_code";
//	}
	
	
	@PostMapping("test123")
	@ResponseBody
	public Map<String, Object> insertApprovalRequest(@RequestParam Map<String, Object> map) {
		map.put("REQUEST_ID", "AR0000000001");
		map.put("APPROVAL_TYPE", "AN");
		map.put("TITLE", "휴가신청");
		map.put("CONTENT", "개인사유");
		map.put("MEMBER_ID", "2025010050");
		map.put("START_DATE", "2025-01-20");
		map.put("END_DATE", "2025-01-22");
//		map.put("REQUEST_FILE", null);
		map.put("CREATE_DATE", "2025-01-09 12:59:16.811");
//		map.put("UPDATE_DATE", map);
		
		Map<String, Object> message = approvalService.insertApprovalRequest(map);
		
		return message;
	}
}
