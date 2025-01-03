package com.itwillbs.controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		if (commuteMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteMinMaxDate = new HashMap<>();
			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
		}
		
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
	
	// 관리자 출퇴근 기록 조회
	@GetMapping("/commuteHistory")
	public String getCommuteHistory(Model model, Map<String, Object> map) {
		String id = "admin";
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(id);
		
		if (commuteMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteMinMaxDate = new HashMap<>();
			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
		}
		
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/commuteHistory";
	}
	
	// 관리자 출퇴근 기록 조회
	@PostMapping("/getCommuteHistory")
	@ResponseBody
	public List<Map<String, Object>> getCommuteHistory(@RequestParam Map<String, Object> map) {
		map.put("id", "admin");
		List<Map<String, Object>> commuteHistory = attendanceService.getMyCommuteHistory(map);
		
		return commuteHistory;
	}
	
	@GetMapping("/myAttendanceHistory")
	public String getMyAttendanceHistory(Model model, Map<String, Object> map) {
		// session id 20241222 임시
		String id = "20241222";
		map.put("id", id);
		
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(id);
		
		if (commuteMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteMinMaxDate = new HashMap<>();
			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
		}
		
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
		
		if (commuteTimeMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteTimeMinMaxDate = new HashMap<>();
			commuteTimeMinMaxDate.put("MIN_STARTING_DATE", now);
			commuteTimeMinMaxDate.put("MAX_STARTING_DATE", now);
		}
		
		model.addAttribute("commuteTimeMinMaxDate", commuteTimeMinMaxDate);
		
		return "/attendance/myAttendanceTime";
	}
	
	@PostMapping("/getMyAttendanceTime")
	@ResponseBody
	public List<Map<String, Object>> getMyAttendanceTime(@RequestParam Map<String, Object> map) {
		String id = "20241222";
		map.put("id", id);
		
		List<Map<String, Object>> commuteTime = attendanceService.getMyCommuteTime(map);
		
		return commuteTime;
	}
	
	// 관리자 출퇴근 기준 시간 관리
	@GetMapping("/attendanceTime")
	public String attendanceTime(Model model) {
		String id = "admin";
		
		Map<String, Object> commuteTimeMinMaxDate = attendanceService.getMyCommuteTimeMinMaxDate(id);
		
		if (commuteTimeMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteTimeMinMaxDate = new HashMap<>();
			commuteTimeMinMaxDate.put("MIN_STARTING_DATE", now);
			commuteTimeMinMaxDate.put("MAX_STARTING_DATE", now);
		}
		
		model.addAttribute("commuteTimeMinMaxDate", commuteTimeMinMaxDate);
		
		return "/attendance/attendanceTime";
	}
	
	@PostMapping("/getAttendanceTime")
	@ResponseBody
	public List<Map<String, Object>> getAttendanceTime(@RequestParam Map<String, Object> map) {
		String id = "admin";
		map.put("id", id);
		
		List<Map<String, Object>> commuteTime = attendanceService.getMyCommuteTime(map);
		
		return commuteTime;
	}
	
	@PostMapping("/deleteAttendanceTime")
	@ResponseBody
	public Map<String, Object> deleteAttendanceTime(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = attendanceService.deleteAttendanceTime(map);
		
		return message;
	}
	
	@PostMapping("/Holiday")
	@ResponseBody
	public String postMethodName(@RequestBody Map<String, String> param) {
		
		System.out.println(param);
		
		return "";
	}
	
	@PostMapping("/addAttendanceTime")
	@ResponseBody
	public Map<String, Object> addAttendanceTime(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = attendanceService.addAttendanceTime(map);
		
		return message;
	}
	
	@PostMapping("/selectBox")
	@ResponseBody
	public List<Map<String, Object>> selectBox(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> selectBoxList = attendanceService.getSelectBoxList(map);
		
		return selectBoxList;
	}
	
}



