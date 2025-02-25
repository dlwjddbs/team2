package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	// xml의 id값과 동일한 메서드명 사용 필수
	String selectTest();

	List<Map<String, Object>> selectToastTest();
	
	int countExistingIds(List<Map<String, Object>> createdRows);

	int insertToastTest(List<Map<String, Object>> createdRows);

	int deleteToastTest(List<String> idList);

	int updateToastTest(List<Map<String, Object>> updatedRows);
	
	List<String> getColumnNames(String tableName);
	
	Map<String, Object> getColumnTypes(String tableName);
	
	List<Map<String, Object>> selectToastTestById(List<String> idList);
	
	int insertToastTestById(List<Map<String, Object>> dataToInsert);
	
	int updateToastTestById(List<Map<String, Object>> dataToUpdate);
	
	//int updateToastTestById(Map<String, Object> updatedRows);

}
