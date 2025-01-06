package com.itwillbs.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.CmtMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CmtService {
    private final CmtMapper cmtMapper;

    

	public void getCheckIn(Map<String, Object> map) {
		map.put("member_id", map);
		// 현재 시간을 "yyyy-MM-dd HH:mm:ss" 형식으로 포맷팅하여 String으로 변환
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    String checkInTime = sdf.format(new java.util.Date());
	    map.put("checkInTime", checkInTime); // varchar2로 저장될 수 있도록 String 값으로 저장
	    
	    map.put("createDate", new Timestamp(System.currentTimeMillis()));

	    
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	    String checkInDate = sdf1.format(new java.util.Date());
	    map.put("checkInDate", checkInDate); 
	    String checkOutDate = sdf1.format(new java.util.Date());
	    map.put("checkOutDate", checkOutDate); 

		cmtMapper.getCheckIn(map);
		
		
		// createDate도 동일하게 처리
//	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    String createDate = sdf1.format(new java.util.Date());
//	    map.put("createDate", createDate); // varchar2로 저장될 수 있도록 String 값으로 저장
	}


	public void updateCheckOut(Map<String, Object> map) {
//		 map.put("member_id", "20241222");
		 
		// checkOutTime을 "yyyy-MM-dd HH:mm:ss" 형식의 String으로 변환
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    String checkOutTime = sdf.format(new java.util.Date());
		    map.put("checkOutTime", checkOutTime); // varchar2로 저장될 수 있도록 String 값으로 저장
		    
//		    map.put("checkOutTime", new Timestamp(System.currentTimeMillis()));
		    cmtMapper.updateCheckOut(map);
	}
	
	
	public Map<String, Object> getTodayHistory(Map<String, Object> map) {
		
		return cmtMapper.selectTodayHistory(map);
	}

}
