package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryMapper {
	
	// xml의 id값과 동일한 메서드명 사용 필수
	
	// 급여 조회
	List<Map<String, Object>> selectSalaryList(Map<String, Object> map);
	
	// 급여 조회 - 사원ID에 해당하는 급여정보를 조회
	Map<String, Object> selectSalaryListById(String id);
	
	// 급여 입력 - 급여조회에서 최대최소일 조회
	Map<String, Object> getSalaryListMinMaxDate(String id);
	
	// 급여 입력
	void insertSalary(Map<String, Object> param);
	
	// 급여 수정 - 사원의 기본금 수정
	void updateMemberSalary(Map<String, Object> map);
	
	// 급여 수정 - 사원의 상여금(보너스) 수정
	void updateSalaryBonus(Map<String, Object> map);
}
