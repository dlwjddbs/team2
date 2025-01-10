package com.itwillbs.controller;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.service.LeaveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;



@Controller
@RequiredArgsConstructor
@Log
public class LeaveController {
	
	private final LeaveService leaveService;
	
	@GetMapping("/leaveList")
	public String memberList() {
		return "/leave/admin/leave_manage";
	}
	
	@PostMapping("/getleaveList")
	public List<Map<String, Object>> getleaveList() {
		List<Map<String, Object>> leaveList = leaveService.getleaveList();
		System.out.println(leaveList);
		return leaveList;
	}
	
	
}
