package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SalaryMapper {
	
	// xml의 id값과 동일한 메서드명 사용 필수
	
	// 사원 조회
	List<Map<String, Object>> selectSalaryMember();

	// 급여 조회
	List<Map<String, Object>> selectSalaryList(Map<String, Object> map);
	
	// 급여 조회 - 사원ID에 해당하는 급여정보를 조회
//	Map<String, Object> selectSalaryListById(String id);

	// 급여 조회 - 급여 입력시 한 사원의 한 데이터만 조회되도록
	Map<String, Object> selectSalaryWriteById(Map<String, Object> salaryMap);
	
	// 급여 조회 - 사원ID에 해당하는 급여정보를 조회 (귀속연월 추가)
	Map<String, Object> selectSalaryListById(Map<String, Object> salaryMap);
	
	// 급여 조회 - 사원ID에 해당하는 달별 야간수당을 조회 (귀속연월 추가)
	Map<String, Object> selectNightBonusById(Map<String, Object> salaryMap);
	
	// 급여 조회 - 전체 사원의 급여 이체 정보 조회
	List<Map<String, Object>> selectSalaryTransferList(Map<String, Object> map);
	
	// 급여 입력 - 급여 조회에서 최대최소일 조회
	Map<String, Object> getSalaryListMinMaxDate(String id);
	
	// 급여 입력
	void insertSalary(Map<String, Object> param);
	
	// 급여 수정 - 사원의 기본급 수정
	void updateMemberSalary(Map<String, Object> map);
	
	// 급여 수정 - 사원의 상여금(보너스) 수정
	void updateSalaryBonus(Map<String, Object> map);
	
	// 급여 삭제 - 사원의 급여 삭제
	void deleteSalary(Map<String, Object> map);

	// 급여 확정 - 버튼 비활성화
	void updatefixedSalary(Map<String, Object> param);



}
