package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.SalaryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaryService {
	private final SalaryMapper salaryMapper;
	
	// 사원 조회(관리자)
	public List<Map<String, Object>> getSalaryMember() {
		return salaryMapper.selectSalaryMember();
	}

	// 입력된 급여 조회(관리자)
	public List<Map<String, Object>> getSalaryList(Map<String, Object> map) {
		return salaryMapper.selectSalaryList(map);
	}
	
	// 급여 입력
	public void writeSalary(Map<String, Object> param) {
		salaryMapper.insertSalary(param);
	}
	
	// 급여 조회 - 사원 ID의 급여 정보를 조회
//	public Map<String, Object> findSalaryById(String id) {
//		return salaryMapper.selectSalaryListById(id);
//	}
	
	// 급여 조회 - 급여 입력시 한 사원의 한 데이터만 조회되도록
	public Map<String, Object> findSalaryWriteById(Map<String, Object> salaryMap) {
		return salaryMapper.selectSalaryWriteById(salaryMap);
	}

	// 급여 조회 - 사원 ID의 급여 정보를 조회 (귀속연월 추가)
	public Map<String, Object> findSalaryById(Map<String, Object> salaryMap) {
		return salaryMapper.selectSalaryListById(salaryMap);
	}
	
	// 급여 조회 - 사원ID의 달별 야간수당을 조회 (귀속연월 추가)
	public Map<String, Object> findNightBonusById(Map<String, Object> salaryMap) {
		return salaryMapper.selectNightBonusById(salaryMap);
	}
	
	// 급여 조회 - 전체 사원의 급여 이체 정보 조회
	public List<Map<String, Object>> getSalaryTransferList(Map<String, Object> map) {
		return salaryMapper.selectSalaryTransferList(map);
	}
	
	// 급여 입력 - 입력된 날짜 조회
	public Map<String, Object> getSalaryListMinMaxDate(String id) {
		return salaryMapper.getSalaryListMinMaxDate(id);
	}

	// 급여 수정
	public void updateSalary(Map<String, Object> map) {
		// 사원의 기본금 수정
		salaryMapper.updateMemberSalary(map);
		// 사원의 상여금 수정
		salaryMapper.updateSalaryBonus(map);

	}
	
	// 급여 확정 상태 변경
	public void updatefixedSalary(Map<String, Object> param) {
		salaryMapper.updatefixedSalary(param);
	}
	

}
