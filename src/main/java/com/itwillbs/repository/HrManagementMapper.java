package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface HrManagementMapper {
	// xml의 id값과 동일한 메서드명 사용 필수
	
	List<Map<String, Object>> selectMemberList();
	
	// 은행, 부서, 직급
	List<Map<String, Object>> selectOrganizationData();
	
	// 테이블 조회
	List<Map<String, Object>> seletTable(@Param("table_name") String table_name, @Param("col1") String col1, @Param("col2") String col2);
	
	// 부서, 상위 하위 select
	List<Map<String, Object>> selectDepartment();
	
	// 사원 신규 등록
	int insertMember(Map<String, Object> param);

	void insertHistory(Map<String, Object> param, @Param("table_name") String string);

	Map<String, Object> selectMember(Map<String, Object> map);
	
	// 사원 상세 정보 수정
	int updateMember(Map<String, Object> param);
}
