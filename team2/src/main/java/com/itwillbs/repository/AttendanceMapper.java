package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {

	List<Map<String, Object>> getMyCommuteHistory(Map<String, Object> map);
	
	Map<String, Object> getMyCommuteHistoryMinMaxDate(Map<String, Object> map);

	List<Map<String, Object>> getMyAttendanceHistory(Map<String, Object> map);

	List<Map<String, Object>> getMyCommuteTime(Map<String, Object> map);

	Map<String, Object> getMyCommuteTimeMinMaxDate(Map<String, Object> map);

	List<Map<String, Object>> getMyAttendanceHistoryDonutChart(Map<String, Object> map);

	int deleteAttendanceTime(Map<String, Object> map);

	Boolean isDeletableAttendanceTime(Map<String, Object> map);

	int isDuplicateAttendanceTime(Map<String, Object> map);

	int insertAttendanceTime(Map<String, Object> map);
	
	List<Map<String, Object>> getSelectBoxList(Map<String, Object> map);

	int insertCheckInTime(Map<String, Object> map);

	int insertCheckOutTime(Map<String, Object> map);

	double getWeekendTotalWorkHour(Map<String, Object> map);

	List<Map<String, Object>> getMemberSelectBoxList(Map<String, Object> map);

	int deleteCommuteTime(Map<String, Object> map);

	int isDuplicateCommuteTime(Map<String, Object> map);

	int insertCommuteTime(Map<String, Object> map);

	List<Map<String, Object>> getUserInfo(Map<String, Object> map);
}
