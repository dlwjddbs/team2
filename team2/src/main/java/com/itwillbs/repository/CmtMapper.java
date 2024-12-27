package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper	
public interface CmtMapper {
		 
//	Map<String, Object> getCheckIn(Map<String, Object> map);
	void getCheckIn(Map<String, Object> map);
	
		
	}

