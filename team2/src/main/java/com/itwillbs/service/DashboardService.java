package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.DashboardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
	
	private final DashboardMapper dashboardMapper;

	public List<Map<String, Object>> getMyCommuteHistory(Map<String, Object> map) {
		return dashboardMapper.getMyCommuteHistory(map);
	}
	
	public Map<String, Object> getMyCommuteHistoryMinMaxDate(Map<String, Object> map) {
		return dashboardMapper.getMyCommuteHistoryMinMaxDate(map);
	}

	public List<Map<String, Object>> getMyAttendanceHistory(Map<String, Object> map) {
		return dashboardMapper.getMyAttendanceHistory(map);
	}

	public Map<String, Object> getMyCommuteTimeMinMaxDate(Map<String, Object> map) {
		return dashboardMapper.getMyCommuteTimeMinMaxDate(map);
	}
	
	public List<Map<String, Object>> getMyCommuteTime(Map<String, Object> map) {
		return dashboardMapper.getMyCommuteTime(map);
	}

	public List<Map<String, Object>> getMyAttendanceHistoryDonutChart(Map<String, Object> map) {
		return dashboardMapper.getMyAttendanceHistoryDonutChart(map);
	}

	public List<Map<String, Object>> getSelectBoxList(Map<String, Object> map) {
		return dashboardMapper.getSelectBoxList(map);
	}

	public List<Map<String, Object>> getMemberSelectBoxList(Map<String, Object> map) {
		return dashboardMapper.getMemberSelectBoxList(map);
	}

	public List<Map<String, Object>> getAttendanceHistoryStackedBarChart(Map<String, Object> map) {
		return dashboardMapper.getAttendanceHistoryStackedBarChart(map);
	}
	
	// 막대기 차트(발주(입고))
	
	// 거래처별 발주량 및 불량률
	
	// 거래처별 불량률 차트
	
}
