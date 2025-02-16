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
	
	int deleteBomDetailById(List<String> idList);
	
	List<Map<String, Object>> selectBomDetail(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectBomDetailByItem(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectItemAndMaterial(Map<String, Object> requestData);
	
	int addBomDetail(List<Map<String, Object>> createdRows);
	
	int updateBomQuantity(Map<String, Object> map);

	int deleteBomDetail(List<String> bomDetailIds);

}
