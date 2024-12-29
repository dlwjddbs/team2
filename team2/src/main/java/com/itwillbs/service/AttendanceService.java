package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.AttendanceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {
	
	private final AttendanceMapper attendanceMapper;

	public List<Map<String, Object>> getMyCommuteHistory(Map<String, Object> map) {
		return attendanceMapper.getMyCommuteHistory(map);
	}
	
	public Map<String, Object> getMyCommuteHistoryMinMaxDate(String id) {
		return attendanceMapper.getMyCommuteHistoryMinMaxDate(id);
	}

	public List<Map<String, Object>> getMyAttendanceHistory(Map<String, Object> map) {
		return attendanceMapper.getMyAttendanceHistory(map);
	}
}
