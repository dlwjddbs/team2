package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BomMapper {
	
	List<Map<String, Object>> selectBom();
	
	int insertBom(List<Map<String, Object>> createdRows);
	
	int updateBom(List<Map<String, Object>> updatedRows);
	
	int deleteBom(List<String> idList);
	
	List<Map<String, Object>> selectBomDetail(Map<String, Object> requestData);
	
	int addBomDetail(List<Map<String, Object>> createdRows);

	int deleteBomDetail(List<String> equipmentIds);

	int deleteBomDetailByBomId(List<String> idList);

}
