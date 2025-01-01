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
		
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(id);
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/myCommuteHistory";
	}
	
	@PostMapping("/getMyCommuteHistory")
	@ResponseBody
	public List<Map<String, Object>> getMyCommuteHistory(@RequestParam Map<String, Object> map) {
		String id = "20241222";
		map.put("id", id);
		List<Map<String, Object>> commuteHistory = attendanceService.getMyCommuteHistory(map);
		
		return commuteHistory;
	}
	
	@GetMapping("/myAttendanceHistory")
	public String getMyAttendanceHistory(Model model, Map<String, Object> map) {
		// session id 20241222 임시
		String id = "20241222";
		map.put("id", id);
		
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(id);
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/myAttendanceHistory";
	}
	
	@PostMapping("/getMyAttendanceHistory")
	@ResponseBody
	public List<Map<String, Object>> getMyAttendanceHistory(@RequestParam Map<String, Object> map) {
		String id = "20241222";
		map.put("id", id);
		List<Map<String, Object>> attendanceHistory = attendanceService.getMyAttendanceHistory(map);
		
		return attendanceHistory;
	}
	
	@PostMapping("/getMyAttendanceHistoryDonutChart")
	@ResponseBody
	public List<Map<String, Object>> getMyAttendanceHistoryDonutChart(@RequestParam Map<String, Object> map) {
		String id = "20241222";
		map.put("id", id);
		List<Map<String, Object>> attendanceHistoryDonut = attendanceService.getMyAttendanceHistoryDonutChart(map);
		
		return attendanceHistoryDonut;
	}
	
	@GetMapping("/myAttendanceTime")
	public String getMyAttendanceTime(Model model) {
		// session id 20241222 임시
		String id = "20241222";
		
		Map<String, Object> commuteTimeMinMaxDate = attendanceService.getMyCommuteTimeMinMaxDate(id);
		model.addAttribute("commuteTimeMinMaxDate", commuteTimeMinMaxDate);
		
		return "/attendance/myCommuteTime";
	}
	
	@PostMapping("/getMyAttendanceTime")
	@ResponseBody
	public List<Map<String, Object>> getMyAttendanceTime(@RequestParam Map<String, Object> map) {
		String id = "20241222";
		map.put("id", id);
		
		List<Map<String, Object>> commuteTime = attendanceService.getMyCommuteTime(map);
		
		return commuteTime;
	}
	
	@GetMapping("/commuteHistory")
	public String getCommuteHistory(Model model) {
		// session id 20241222 임시
		String id = "20241222";
		
		Map<String, Object> commuteTimeMinMaxDate = attendanceService.getMyCommuteTimeMinMaxDate(id);
		model.addAttribute("commuteTimeMinMaxDate", commuteTimeMinMaxDate);
		
		return "/attendance/commuteHistory";
	}
}



