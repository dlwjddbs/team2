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
	
	// 입력된 급여 조회(관리자)
	public List<Map<String, Object>> getSalaryList(Map<String, Object> map) {
		return salaryMapper.selectSalaryList(map);
	}

	public int writeSalary(Map<String, Object> param) {
		System.out.println("service param : " + param);
		return salaryMapper.insertSalary(param);
	}
	
	// 급여 조회 - 사원 ID의 급여 정보를 조회
	public Map<String, Object> findSalaryById(String id) {
		return salaryMapper.selectSalaryListById(id);
	}
	
	// 급여 입력 - 입력된 날짜 조회
	public Map<String, Object> getSalaryListMinMaxDate(String id) {
		return salaryMapper.getSalaryListMinMaxDate(id);
	}



}
