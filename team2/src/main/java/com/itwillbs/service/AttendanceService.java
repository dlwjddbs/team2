package com.itwillbs.service;

import java.util.HashMap;
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

	public Map<String, Object> getMyCommuteTimeMinMaxDate(String id) {
		return attendanceMapper.getMyCommuteTimeMinMaxDate(id);
	}
	
	public List<Map<String, Object>> getMyCommuteTime(Map<String, Object> map) {
		return attendanceMapper.getMyCommuteTime(map);
	}

	public List<Map<String, Object>> getMyAttendanceHistoryDonutChart(Map<String, Object> map) {
		return attendanceMapper.getMyAttendanceHistoryDonutChart(map);
	}

	public Map<String, Object> deleteAttendanceTime(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		Boolean is_deletable = attendanceMapper.isDeletableAttendanceTime(map);
		if (is_deletable) {
			attendanceMapper.deleteAttendanceTime(map);
			message.put("result", "삭제 되었습니다.");
			message.put("resultCode", "1");
			
			return message;
		}
		message.put("result", "오늘 이하의 적용시작일은 삭제할 수 없습니다.");
		message.put("resultCode", "0");
		
		return message;
	}

}
