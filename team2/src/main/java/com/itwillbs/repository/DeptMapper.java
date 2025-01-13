package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {

	List<Map<String, Object>> getUpperDept(Map<String, Object> map);

	int isDuplicateUpperDept(Map<String, Object> map);

	int insertUpperDept(Map<String, Object> map);

	List<Map<String, Object>> getCcodeList(Map<String, Object> map);

	List<Map<String, Object>> getDepMngList(Map<String, Object> map);

}
