package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalMapper {

	void insertApprovalRequest(Map<String, Object> map);

	List<Map<String, Object>> selectApprovalLine(String request_id);

	int insertApprovalStep(Map<String, String> tmpMap);

	List<Map<String, Object>> selectApprovalPendingList(Map<String, Object> map);

	List<Map<String, Object>> approvalRequestDetail(Map<String, Object> map);

	int approveApprovalRequest(Map<String, Object> map);

	int pendingApprovalRequest(Map<String, Object> map);

	void returnApprovalRequest(Map<String, Object> map);

	void psRejectedApprovalRequest(Map<String, Object> map);

	Map<String, Object> getMyCommuteHistoryMinMaxDate(Map<String, Object> map);
	
}
