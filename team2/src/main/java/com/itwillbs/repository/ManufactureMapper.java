package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManufactureMapper {
	
	List<Map<String, Object>> selectWorkcenter();
//	
//	int countExistingIds(List<Map<String, Object>> createdRows);
//
//	int insertToastTest(List<Map<String, Object>> createdRows);
//
//	int deleteToastTest(List<String> idList);
//
//	int updateToastTest(List<Map<String, Object>> updatedRows);
//	
//	List<String> getColumnNames(String tableName);
//	
//	Map<String, Object> selectToastTestById(String id);
//	
//	int updateToastTestById(Map<String, Object> updatedRows);

}
