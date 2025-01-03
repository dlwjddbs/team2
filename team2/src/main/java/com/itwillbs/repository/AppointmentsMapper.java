package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentsMapper {
	
	 Map<String, String> getMinMaxDate();
	
    List<Map<String, Object>> getAppointList(Map<String, Object> params);
    
}
