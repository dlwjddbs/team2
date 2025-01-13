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
		map.put("MEMBER_ID", "2025010050");
		map.put("START_DATE", "2025-01-27");
		map.put("END_DATE", "2025-01-27");
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
//        	return "redirect:" + request.getHeader("Referer");
        	return "redirect:/login";
        }
		
		String id = session.getAttribute("id").toString();
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
	
	@PostMapping("approvalRequestDetail")
	@ResponseBody
	public List<Map<String, Object>> approvalRequestDetail(@RequestParam Map<String, Object> map) {
		
		List<Map<String, Object>> approvalDetail = approvalService.approvalRequestDetail(map);
		
		return approvalDetail;
	}
	
	@PostMapping("/approveApprovalRequest")
	@ResponseBody
	public Map<String, Object> approveApprovalRequest(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("APPROVER_ID", id);
		
		Map<String, Object> message = approvalService.approveApprovalRequest(map);
		
		return message;
	}
	
	@PostMapping("/returnApprovalRequest")
	@ResponseBody
	public Map<String, Object> returnApprovalRequest(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("APPROVER_ID", id);
		
		Map<String, Object> message = approvalService.returnApprovalRequest(map);
		
		return message;
	}
	
	@PostMapping("/cancelApprovalRequest")
	@ResponseBody
	public Map<String, Object> cancelApprovalRequest(HttpSession session, @RequestParam Map<String, Object> map) {
		Map<String, Object> message = approvalService.cancelApprovalRequest(map);
		
		return message;
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
	public String approvalRequestCompletion(HttpServletRequest request, HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
		
		if (!session.getAttribute("authority").toString().equals("ADM") 
        			&& request.getHeader("Referer") != null) {
//        	return "redirect:" + request.getHeader("Referer");
        	return "redirect:/login";
        }
		
		String id = session.getAttribute("id").toString();
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
	
	@PostMapping("selectApprovalCompletionList")
	@ResponseBody
	public List<Map<String, Object>> selectApprovalCompletionList(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		List<Map<String, Object>> approvalList = approvalService.selectApprovalCompletionList(map);
		
		return approvalList;
	}
	
	@GetMapping("/myApprovalRequestCompletion")
	public String myApprovalRequestCompletion(HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
		
		String id = session.getAttribute("id").toString();
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
	
	@PostMapping("selectMyApprovalList")
	@ResponseBody
	public List<Map<String, Object>> selectMyApprovalList(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		List<Map<String, Object>> approvalList = approvalService.selectMyApprovalList(map);
		
		return approvalList;
	}
	
	@GetMapping("/myApprovalRequestStandby")
	public String myApprovalRequestStandby(HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
		
		String id = session.getAttribute("id").toString();
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
