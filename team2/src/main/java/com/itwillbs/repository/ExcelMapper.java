package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelMapper {
	// xml의 id값과 동일한 메서드명 사용 필수

	List<Map<String, Object>> selectExistingData(String tableName);
	
	int insertExcelData(String tableName, List<Map<String, Object>> createdRows);
	
	int updateExcelData(String tableName, String tableCodeId, List<Map<String, Object>> dataToUpdate);
	
	int deleteExcelData(String tableName, String tableCodeId, List<String> idList);

}
