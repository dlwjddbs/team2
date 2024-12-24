package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {

	List<Map<String, Object>> getMyCommuteHistory(String id);
	
}
