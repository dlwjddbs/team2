package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentsMapper {

	// date range 기간설정
	Map<String, String> getMinMaxDate();

	// 인사 발령 내역 조회
	List<Map<String, Object>> getAppointList(Map<String, Object> params);

	// 모든 부서 조회
    List<Map<String, Object>> getAllDepartments();

    // 모든 직급 조회
	List<Map<String, Object>> getAllRanks();

	//조직도 조회
	List<Map<String, Object>> getOrgTree();
}
