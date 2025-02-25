package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeaveMapper {

	List<Map<String, Object>> getLeaveList(Map<String, Object> map);

	List<Map<String, Object>> getLeaveHisList(Map<String, Object> map);
	
}
