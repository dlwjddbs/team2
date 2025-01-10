package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalMapper {

	void insertApprovalRequest(Map<String, Object> map);

	List<Map<String, Object>> selectApprovalLine(String request_id);

	int insertApprovalStep(Map<String, String> tmpMap);
	
}
