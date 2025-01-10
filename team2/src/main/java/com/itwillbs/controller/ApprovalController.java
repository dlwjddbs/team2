package com.itwillbs.controller;

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
	
	@GetMapping("/approvalRequestList")
	public String approvalRequestList(HttpSession session) {
		
		return "/approval/approvalRequestList";
	}
	
	
	@PostMapping("createApprovalRequest")
	@ResponseBody
	public Map<String, Object> createApprovalRequest(@RequestParam Map<String, Object> map) {
		map.put("APPROVAL_TYPE", "AN");
		map.put("TITLE", "휴가신청");
		map.put("CONTENT", "개인사유");
		map.put("MEMBER_ID", "2025010052");
		map.put("START_DATE", "2025-01-20");
		map.put("END_DATE", "2025-01-22");
		map.put("REQUEST_FILE", null);
		
		Map<String, Object> message = approvalService.createApprovalRequest(map);
		
		return message;
	}
}
