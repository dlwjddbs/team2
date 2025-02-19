package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RequestMapper {

    // 전체 출하 목록 조회
    List<Map<String, Object>> getRequest();

    // 출하 상세 정보 조회
    List<Map<String, Object>> getRequest(@Param("requestId") String requestId);
    
    // 필터링된 출하 조회
	List<Map<String, Object>> selectFilteredRequest(Map<String, Object> filters);
    
	// 출하 등록
	void insertRequest(@Param("list") List<Map<String, Object>> list);

	// 출하 수정
	void updateRequest(Map<String, Object> orderData);

	// 출하 삭제
    void deleteRequest(List<String> orderIds);
}
