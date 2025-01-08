package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	// MEMBER_HISTORY에 변경 이력 삽입
    void insertMemberHistory(Map<String, Object> historyData);

    // 일반 발령 처리 (부서/직급 업데이트)
    void updateMember(Map<String, Object> memberData);

    // 퇴사 처리
    void updateMemberForResign(@Param("MEMBER_ID") String memberId, @Param("CHANGE_DATE") String changeDate);

    void updateChangedColumns(Map<String, Object> changes);
	
}
