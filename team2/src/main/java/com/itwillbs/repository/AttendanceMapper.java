package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {

	List<Map<String, Object>> getMyCommuteHistory(Map<String, Object> map);
	
	Map<String, Object> getMyCommuteHistoryMinMaxDate(String id);

	List<Map<String, Object>> getMyAttendanceHistory(Map<String, Object> map);

	List<Map<String, Object>> getMyCommuteTime(Map<String, Object> map);

	Map<String, Object> getMyCommuteTimeMinMaxDate(String id);

	List<Map<String, Object>> getMyAttendanceHistoryDonutChart(Map<String, Object> map);

	void deleteAttendanceTime(Map<String, Object> map);

	Boolean isDeletableAttendanceTime(Map<String, Object> map);

	int isDuplicateAttendanceTime(Map<String, Object> map);

	int insertAttendanceTime(Map<String, Object> map);
	
}
