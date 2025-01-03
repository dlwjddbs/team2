package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryMapper {
	
	// xml의 id값과 동일한 메서드명 사용 필수
	
	List<Map<String, Object>> selectSalaryList();

	Map<String, Object> getSalaryHistoryMinMaxDate();

	List<Map<String, Object>> getSalaryHistory(Map<String, Object> map);

	int insertSalary(Map<String, Object> param);
	
	// 급여 정보로 수정 예정 (mapper 추가 필요)
	Map<String, Object> selectSalaryListById(String id);

	List<Map<String, Object>> selectSalaryInputList(Map<String, Object> map);

	Map<String, Object> getSalaryInputMinMaxDate(String id);

}
