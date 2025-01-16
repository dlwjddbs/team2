package com.itwillbs.controller;


import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.LeaveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;



@Controller
@RequiredArgsConstructor
@Log
public class LeaveController {
	
	private final LeaveService leaveService;
	
	@GetMapping("/admin/leaveList")
	public String memberList(@AuthenticationPrincipal User user) {
		if (user == null) {
            return "redirect:/login"; 
        }
		
		return "/leave/admin/leave_manage";
	}
	
	@PostMapping("/ajax/getLeaveList")
	@ResponseBody
	public List<Map<String, Object>> getleaveList(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> leaveList = leaveService.getleaveList(map);
		return leaveList;
	}
	
	@PostMapping("/ajax/getLeaveHisList")
	@ResponseBody
	public List<Map<String, Object>> getleaveHisList(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> leaveHisList = leaveService.getleaveHisList(map);
		return leaveHisList;
	}
	
	
}
