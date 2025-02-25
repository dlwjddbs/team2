package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExcelMapper {
	// xml의 id값과 동일한 메서드명 사용 필수

	List<Map<String, Object>> selectExistingData(@Param("tableName") String tableName, 
												 @Param("columns") List<String> validColumns);

	int insertExcelData(@Param("tableName") String tableName,
						@Param("dataList") List<Map<String, Object>> dataToInsert);

	int updateExcelData(@Param("tableName") String tableName, 
						@Param("tableCodeId") String tableCodeId,
						@Param("dataList") List<Map<String, Object>> dataToUpdate);

	int deleteExcelData(@Param("tableName") String tableName, 
						@Param("tableCodeId") String tableCodeId,
						@Param("keyValues") List<String> dataToDelete);

}
