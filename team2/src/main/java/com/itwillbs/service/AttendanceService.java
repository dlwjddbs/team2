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

	public List<Map<String, Object>> getMyCommuteHistory(String id) {
		return attendanceMapper.getMyCommuteHistory(id);
	}
	
}
