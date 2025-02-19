package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.DashboardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class DashboardController {
	
	private final DashboardService dashboardService;
	
	// 대시보드 테스트 페이지
	// 설비 정보로 테스트
	@GetMapping("/testView")
	public String testView() {
		return "/dashboard/testView";
	}
	
	// 대시보드 예시 페이지 (전체 현황 예시
	@GetMapping("/testView2")
	public String testView2() {
		return "/dashboard/testView2";
	}
	
	// 대시보드 예시 페이지 (생산 현황 예시)
	@GetMapping("/testView3")
	public String testView3() {
		return "/dashboard/testView3";
	}
	
	@PostMapping("/dashboard/selectBox")
	@ResponseBody
	public List<Map<String, Object>> selectBox(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> selectBoxList = dashboardService.getSelectBoxList(map);
		
		return selectBoxList;
	}
	
	@PostMapping("/dashboard/memberSelectBox")
	@ResponseBody
	public List<Map<String, Object>> memberSelectBox(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> memberSelectBoxList = dashboardService.getMemberSelectBoxList(map);
		
		return memberSelectBoxList;
	}
	
	// 도넛 차트
	@PostMapping("/dashboard/getEquipmentDonutChart")
	@ResponseBody
	public List<Map<String, Object>> getEquipmentDonutChart(@RequestParam Map<String, Object> map) {
		
		List<Map<String, Object>> attendanceHistoryDonut = dashboardService.getMyAttendanceHistoryDonutChart(map);
		
		return attendanceHistoryDonut;
	}
	
	// 막대기 차트
	@PostMapping("/dashboard/getEquipmentStackedBarChart")
	@ResponseBody
	public List<Map<String, Object>> getEquipmentStackedBarChart(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> attendanceHistoryStackedBar = dashboardService.getAttendanceHistoryStackedBarChart(map);
		
		return attendanceHistoryStackedBar;
	}
	
}