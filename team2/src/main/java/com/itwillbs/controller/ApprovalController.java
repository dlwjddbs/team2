package com.itwillbs.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.ApprovalService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ApprovalController {
	
	private final ApprovalService approvalService;
	
	@GetMapping("/createApprovalRequest")
	public String createApprovalRequest(HttpSession session) {
		
		return "/approval/createApprovalRequest";
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
	
	@GetMapping("/approvalRequestStandby")
	public String approvalRequestStandby(HttpServletRequest request, HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
		
		if (!session.getAttribute("authority").toString().equals("ADM") 
        			&& request.getHeader("Referer") != null) {
        	return "redirect:" + request.getHeader("Referer");
        }
		
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
//		Map<String, Object> commuteMinMaxDate = approvalService.getMyCommuteHistoryMinMaxDate(map);
//		
//		if (commuteMinMaxDate == null) {
//			LocalDate now = LocalDate.now();
//			
//			commuteMinMaxDate = new HashMap<>();
//			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
//			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
//		}
//		
//		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/approval/approvalRequestStandby";
	}
	
	@PostMapping("selectApprovalPendingList")
	@ResponseBody
	public List<Map<String, Object>> selectApprovalPending(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		List<Map<String, Object>> approvalList = approvalService.selectApprovalPendingList(map);
		
		return approvalList;
	}
	
	@GetMapping("/approvalRequestCompletion")
	public String approvalRequestCompletion(HttpSession session) {
		
		return "/approval/approvalRequestCompletion";
	}
	
}
