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

	public List<Map<String, Object>> getSalaryList() {
		return salaryMapper.selectSalaryList();
	}

	public List<Map<String, Object>> getSalaryHistory(Map<String, Object> map) {
		return salaryMapper.getSalaryHistory(map);
	}

	public int writeSalary(Map<String, Object> param) {
		System.out.println("service param : " + param);
		return salaryMapper.insertSalary(param);
	}

	public List<Map<String, Object>> memberSalaryList() {
		return salaryMapper.selectSalaryList();
	}
	
	// 급여 정보로 수정 예정
	public Map<String, Object> findSalaryById(String id) {
		return salaryMapper.selectSalaryListById(id);
	}
	
	// 입력된 급여 조회(관리자)
	public List<Map<String, Object>> salaryInputList(Map<String, Object> map) {
		return salaryMapper.selectSalaryInputList(map);
	}

	public Map<String, Object> getSalaryInputMinMaxDate(String id) {
		return salaryMapper.getSalaryInputMinMaxDate(id);
	}



}
