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

	int checkDuplicateWorkcenterCode(Map<String, Object> map);

	List<Map<String, Object>> selectMember(Map<String, Object> requestData);

	List<Map<String, Object>> selectEquipment(Map<String, Object> requestData);

	int addEquipment(List<Map<String, Object>> createdRows);

	int deleteEquipment(List<String> equipmentIds);

	int deleteEquipmentByWorkcenterId(List<String> idList);

}
