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

	List<Map<String, Object>> selectMember(Map<String, Object> requestData);

	List<Map<String, Object>> selectEquipment(Map<String, Object> requestData);

	int addEquipment(List<Map<String, Object>> createdRows);

	int deleteEquipment(List<String> equipmentIds);

	int deleteEquipmentByWorkcenterId(List<String> idList);

	List<Map<String, Object>> selectProcess(Map<String, Object> requestData);

	int checkDuplicateCode(Map<String, Object> map);

	int insertProcess(List<Map<String, Object>> createdRows);

	int updateProcess(List<Map<String, Object>> updatedRows);

	int deleteProcess(List<String> processIds);

	List<Map<String, Object>> selectRouting(Map<String, Object> requestData);

	List<Map<String, Object>> selectItem(Map<String, Object> requestData);

	int insertRouting(List<Map<String, Object>> createdRows);

	int updateRouting(List<Map<String, Object>> updatedRows);

	int deleteRouting(List<String> processIds);

	List<Map<String, Object>> selectRoutingSequence(Map<String, Object> requestData);

	int insertRoutingSequence(List<Map<String, Object>> createdRows);

	int updateRoutingProcessQuantity(Map<String, Object> map);

	int deleteRoutingSequence(List<String> processIds);

}
