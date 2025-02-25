package com.itwillbs.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
	
	@GetMapping("/approval/createApprovalRequest")
	public String createApprovalRequest(HttpSession session) {
		
		return "/approval/createApprovalRequest";
	}
	
	@PostMapping("/approval/createApprovalRequest")
	@ResponseBody
	public Map<String, Object> createApprovalRequest(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		map.put("TITLE", "휴가신청");
		map.put("MEMBER_ID", user.getUsername());
		
		Map<String, Object> message = approvalService.createApprovalRequest(map);
		
		return message;
	}
	
	@GetMapping("/admin/approvalRequestStandby")
	public String approvalRequestStandby(@AuthenticationPrincipal User user, Model model, Map<String, Object> map) {
		if (user == null) {
            return "redirect:/login"; 
        }
		
		String id = user.getUsername();
		map.put("id", id);
		
		Map<String, Object> approvalRequestMinMaxDate = approvalService.getApprovalRequestStandbyMinMaxDate(map);
		
		if (approvalRequestMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			approvalRequestMinMaxDate = new HashMap<>();
			approvalRequestMinMaxDate.put("APPROVAL_STEP_MAX_DATE", now);
			approvalRequestMinMaxDate.put("APPROVAL_STEP_MIN_DATE", now);
		}
		
		model.addAttribute("approvalRequestMinMaxDate", approvalRequestMinMaxDate);
		
		return "/approval/approvalRequestStandby";
	}
	
	@PostMapping("/approval/approvalRequestDetail")
	@ResponseBody
	public List<Map<String, Object>> approvalRequestDetail(@RequestParam Map<String, Object> map) {
		
		List<Map<String, Object>> approvalDetail = approvalService.approvalRequestDetail(map);
		
		return approvalDetail;
	}
	
	@PostMapping("/approval/approveApprovalRequest")
	@ResponseBody
	public Map<String, Object> approveApprovalRequest(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		String id = user.getUsername();
		map.put("APPROVER_ID", id);
		
		Map<String, Object> message = approvalService.approveApprovalRequest(map);
		
		return message;
	}
	
	@PostMapping("/approval/returnApprovalRequest")
	@ResponseBody
	public Map<String, Object> returnApprovalRequest(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		String id = user.getUsername();
		map.put("APPROVER_ID", id);
		
		Map<String, Object> message = approvalService.returnApprovalRequest(map);
		
		return message;
	}
	
	@PostMapping("/approval/cancelApprovalRequest")
	@ResponseBody
	public Map<String, Object> cancelApprovalRequest(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = approvalService.cancelApprovalRequest(map);
		
		return message;
	}
	
	@PostMapping("/approval/selectApprovalPendingList")
	@ResponseBody
	public List<Map<String, Object>> selectApprovalPending(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		String id = user.getUsername();
		map.put("id", id);
		
		List<Map<String, Object>> approvalList = approvalService.selectApprovalPendingList(map);
		
		return approvalList;
	}
	
	@GetMapping("/admin/approvalRequestCompletion")
	public String approvalRequestCompletion(@AuthenticationPrincipal User user, Model model, Map<String, Object> map) {
		if (user == null) {
            return "redirect:/login"; 
        }
		
		String id = user.getUsername();
		map.put("id", id);
		
		Map<String, Object> approvalRequestMinMaxDate = approvalService.getApprovalRequestCompletionMinMaxDate(map);
		
		if (approvalRequestMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			approvalRequestMinMaxDate = new HashMap<>();
			approvalRequestMinMaxDate.put("APPROVAL_STEP_MAX_DATE", now);
			approvalRequestMinMaxDate.put("APPROVAL_STEP_MIN_DATE", now);
		}
		
		model.addAttribute("approvalRequestMinMaxDate", approvalRequestMinMaxDate);
		
		return "/approval/approvalRequestCompletion";
	}
	
	@PostMapping("/approval/selectApprovalCompletionList")
	@ResponseBody
	public List<Map<String, Object>> selectApprovalCompletionList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		String id = user.getUsername();
		map.put("id", id);
		
		List<Map<String, Object>> approvalList = approvalService.selectApprovalCompletionList(map);
		
		return approvalList;
	}
	
	@GetMapping("/approval/myApprovalRequestCompletion")
	public String myApprovalRequestCompletion(@AuthenticationPrincipal User user, Model model, Map<String, Object> map) {
		if (user == null) {
            return "redirect:/login"; 
        }
		
		String id = user.getUsername();
		map.put("id", id);
		map.put("status", "completion");
		
		Map<String, Object> approvalRequestMinMaxDate = approvalService.getMyApprovalRequestCompletionMinMaxDate(map);
		
		if (approvalRequestMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			approvalRequestMinMaxDate = new HashMap<>();
			approvalRequestMinMaxDate.put("MAX_CREATE_DATE", now);
			approvalRequestMinMaxDate.put("MIN_CREATE_DATE", now);
		}
		
		model.addAttribute("approvalRequestMinMaxDate", approvalRequestMinMaxDate);
		
		return "/approval/myApprovalRequestCompletion";
	}
	
	@PostMapping("/approval/selectMyApprovalList")
	@ResponseBody
	public List<Map<String, Object>> selectMyApprovalList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		String id = user.getUsername();
		map.put("id", id);
		
		List<Map<String, Object>> approvalList = approvalService.selectMyApprovalList(map);
		
		return approvalList;
	}
	
	@GetMapping("/approval/myApprovalRequestStandby")
	public String myApprovalRequestStandby(@AuthenticationPrincipal User user, Model model, Map<String, Object> map) {
		if (user == null) {
            return "redirect:/login"; 
        }
		
		String id = user.getUsername();
		map.put("id", id);
		map.put("status", "pending");
		
		Map<String, Object> approvalRequestMinMaxDate = approvalService.getMyApprovalRequestCompletionMinMaxDate(map);
		
		if (approvalRequestMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			approvalRequestMinMaxDate = new HashMap<>();
			approvalRequestMinMaxDate.put("MAX_CREATE_DATE", now);
			approvalRequestMinMaxDate.put("MIN_CREATE_DATE", now);
		}
		
		model.addAttribute("approvalRequestMinMaxDate", approvalRequestMinMaxDate);
		
		return "/approval/myApprovalRequestStandby";
	}
}
