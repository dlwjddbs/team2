package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper	
public interface CmtMapper {
		 
	void getCheckIn(Map<String, Object> map);
	
	void updateCheckOut(Map<String, Object> map);

	Map<String, Object> getCheckInTime(String memberId);
	}

