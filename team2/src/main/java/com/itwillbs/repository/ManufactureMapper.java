package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManufactureMapper {
	
	List<Map<String, Object>> selectWorkcenter();
	
	int insertWorkcenter(List<Map<String, Object>> createdRows);
	
	int updateWorkcenter(List<Map<String, Object>> updatedRows);
	
	int deleteWorkcenter(List<String> idList);

//	int countExistingIds(List<Map<String, Object>> createdRows);
//	
//	List<String> getColumnNames(String tableName);
//	
//	Map<String, Object> selectToastTestById(String id);
//	
//	int updateToastTestById(Map<String, Object> updatedRows);

}
