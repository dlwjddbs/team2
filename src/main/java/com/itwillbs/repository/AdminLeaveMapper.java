package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminLeaveMapper {
	
	// 전체 직원 연차
	public List<Map<String, Object>> selectLeaveList();
	
	//
	
}
