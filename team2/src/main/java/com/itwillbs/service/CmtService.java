package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.CmtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CmtService {
    private final CmtMapper cmtMapper;

    

	public void getCheckIn(Map<String, Object> map) {
		map.put("member_id", "20241222");
		map.put("id", "C0000000020");
		map.put("checkInTime", new Timestamp(System.currentTimeMillis()));
		map.put("createDate", new Timestamp(System.currentTimeMillis()));
		cmtMapper.getCheckIn(map);
	}



	public void updateCheckOut(Map<String, Object> map) {
		 map.put("member_id", "20241222");
		    map.put("checkOutTime", new Timestamp(System.currentTimeMillis()));
		    cmtMapper.updateCheckOut(map);
	}

	public Map<String, Object> getTodayHistory(Map<String, Object> map) {
		
		return cmtMapper.selectTodayHistory(map);
	}



//	public List<Map<String, Object>> getTodayHistory(String memberId) {
//		
//		return cmtMapper.getTodayHistory(memberId);
//	}
}
