package com.itwillbs.service;

import java.util.HashMap;
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

	public double getWeekendTotalWorkHour(Map<String, Object> map) {
		return dashboardMapper.getWeekendTotalWorkHour(map);
	}

	public List<Map<String, Object>> getMemberSelectBoxList(Map<String, Object> map) {
		return dashboardMapper.getMemberSelectBoxList(map);
	}

	public List<Map<String, Object>> getUserInfo(Map<String, Object> map) {
		return dashboardMapper.getUserInfo(map);
	}

	public List<Map<String, Object>> getAttendanceHistoryStackedBarChart(Map<String, Object> map) {
		return dashboardMapper.getAttendanceHistoryStackedBarChart(map);
	}

	public List<Map<String, Object>> getHoliday(Map<String, Object> map) {
		return dashboardMapper.getHoliday(map);
	}

	public Map<String, Object> getHolidayMinMaxDate(Map<String, Object> map) {
		return dashboardMapper.getHolidayMinMaxDate(map);
	}

	public Map<String, Object> getMyLeave(Map<String, Object> map) {
		return dashboardMapper.getMyLeave(map);
	}

	public List<Map<String, Object>> getLeaveType() {
		return dashboardMapper.getLeaveType();
	}

}
