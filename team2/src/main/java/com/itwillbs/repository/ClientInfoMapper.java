package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientInfoMapper {
	// xml의 id값과 동일한 메서드명 사용 필수
	
		List<Map<String, Object>> selectClientInfo();

		List<Map<String, Object>> selectClientInfoDetail(Map<String, Object> requestData);

		int insertClientInfo(List<Map<String, Object>> createdRows);

		int updateClientInfo(List<Map<String, Object>> updatedRows);

		int updateClientDetail(List<Map<String, Object>> updatedRows);

		int deleteClientInfo(List<String> idList);

		int deleteClientDetail(List<String> idList);
		
		int checkDuplicateClientCode(Map<String, Object> map);



}
