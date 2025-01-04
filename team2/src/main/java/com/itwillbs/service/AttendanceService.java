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
		
		String result = "오늘 이하의 적용시작일은 삭제할 수 없습니다.";
		String resultCode = "0";
		
		if (is_deletable) {
			int resultCnt = attendanceMapper.deleteAttendanceTime(map);
			
			if (resultCnt > 0) {
				result = "삭제 되었습니다.";
				resultCode = "1";
			} else {
				result = "삭제 실패.";
			}
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	public Map<String, Object> addAttendanceTime(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 날짜입니다.";
		String resultCode = "0";
		
		int duplicateCnt = attendanceMapper.isDuplicateAttendanceTime(map);
		if (duplicateCnt == 0) {
			int resultCnt = attendanceMapper.insertAttendanceTime(map);
			if (resultCnt > 0) {
				result = "등록 되었습니다.";
				resultCode = "1";
			} else {
				result = "등록 실패.";
			}
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	public List<Map<String, Object>> getSelectBoxList(Map<String, Object> map) {
		return attendanceMapper.getSelectBoxList(map);
	}

	public Map<String, Object> insertCheckTime(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "등록 실패.";
		String resultCode = "0";
		int resultCnt = 0;
		
		switch(map.get("type").toString()) {
		    case "checkIn": 
		    	resultCnt = attendanceMapper.insertCheckInTime(map);
				if (resultCnt > 0) {
					result = "출근 등록 완료.";
					resultCode = "1";
				}
				
		    	break;
		    case "checkOut": 
		    	resultCnt = attendanceMapper.insertCheckOutTime(map);
				if (resultCnt > 0) {
					result = "퇴근 등록 완료.";
					resultCode = "1";
				}
		    	
		    	break;
		}	
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}
}
