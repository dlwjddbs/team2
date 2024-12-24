package com.itwillbs.controller;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.AttendanceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class AttendanceController {
	
	private final AttendanceService attendanceService;
	
	@GetMapping("/myCommuteHistory")
	public String getMyCommuteHistory(Model model, Map<String, Object> map) {
		// session id 20241222 임시
		String id = "20241222";
		map.put("id", id);
		
		List<Map<String, Object>> commuteHistory = attendanceService.getMyCommuteHistory(map);
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(id);
		model.addAttribute("commuteHistory", commuteHistory);
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/myCommuteHistory";
	}
	
	@PostMapping("/getMyCommuteHistoryLimit")
	@ResponseBody
	public List<Map<String, Object>> getMyCommuteHistoryLimit(@RequestParam Map<String, Object> map) {
		String id = "20241222";
		map.put("id", id);
		List<Map<String, Object>> commuteHistory = attendanceService.getMyCommuteHistory(map);
		
		return commuteHistory;
	}
	
}



