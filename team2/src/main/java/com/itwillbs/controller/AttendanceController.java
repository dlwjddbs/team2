package com.itwillbs.controller;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.AttendanceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class AttendanceController {
	
	private final AttendanceService attendanceService;
	
	@GetMapping("/myCommuteHistory")
	public String getMyCommuteHistory(Model model) {
		// session id 20241222 임시
		String id = "20241222";
		List<Map<String, Object>> commuteHistory = attendanceService.getMyCommuteHistory(id);
		model.addAttribute("commuteHistory", commuteHistory);
		
		return "/attendance/myCommuteHistory";
	}
	
}



