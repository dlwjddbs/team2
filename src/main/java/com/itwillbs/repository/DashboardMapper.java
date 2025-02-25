package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {

	List<Map<String, Object>> getMyCommuteHistory(Map<String, Object> map);
	
	Map<String, Object> getMyCommuteHistoryMinMaxDate(Map<String, Object> map);

	List<Map<String, Object>> getMyAttendanceHistory(Map<String, Object> map);

	List<Map<String, Object>> getMyCommuteTime(Map<String, Object> map);

	Map<String, Object> getMyCommuteTimeMinMaxDate(Map<String, Object> map);

	List<Map<String, Object>> getSelectBoxList(Map<String, Object> map);

	List<Map<String, Object>> getMemberSelectBoxList(Map<String, Object> map);
	
	// ====================================================================================

	List<Map<String, Object>> getMyAttendanceHistoryDonutChart(Map<String, Object> map);

	List<Map<String, Object>> getAttendanceHistoryStackedBarChart(Map<String, Object> map);
	
	// ====================================================================================
	
	List<Map<String, Object>> selectPoBarChartDaily(Map<String, Object> map);
	
	List<Map<String, Object>> selectPoBarChartWeekly(Map<String, Object> map);
	
	List<Map<String, Object>> selectPoBarChartMonthly(Map<String, Object> map);
	
	List<Map<String, Object>> selectClientPo(Map<String, Object> map);
	
	List<Map<String, Object>> selectClientInboundChart(Map<String, Object> map);
	
	List<Map<String, Object>> selectInboundChart(Map<String, Object> map);
}
