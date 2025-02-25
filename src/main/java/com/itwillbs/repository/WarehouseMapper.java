package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehouseMapper {

	List<Map<String, Object>> selectWarehouse();

	int insertWarehouse(List<Map<String, Object>> createdRows);

	int updateWarehouse(List<Map<String, Object>> updatedRows);

	int deleteWarehouse(List<String> idList);

	int checkDuplicateWhseCode(Map<String, Object> map);

	int checkDuplicateLocationCode(Map<String, Object> map);

	List<Map<String, Object>> selectLocation(Map<String, Object> requestData);

	int insertLocation(List<Map<String, Object>> createdRows);

	int updateLocation(List<Map<String, Object>> updatedRows);
	
	int deleteLocation(List<String> locationIds);




}
