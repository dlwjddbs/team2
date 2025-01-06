package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.AdminLeaveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
@Log
public class AdminLeaveController {
	
	private final AdminLeaveService adminLeaveService;
	
	@GetMapping("/leaveManage")
	public String getLeaveManage() {
		return "/leave/admin/leave_manage";
	}
	
	@PostMapping("/getLeaveList")
	@ResponseBody
	public List<Map<String, Object>> getLeaveList() {
		List<Map<String, Object>> leave_list = adminLeaveService.getLeaveList();
		return leave_list;
	}
	
	
	
}
