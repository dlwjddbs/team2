package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EquipmentMapper {
	
	// xml의 id값과 동일한 메서드명 사용 필수
	
	List<Map<String, Object>> selectEquipment();
	
	int insertEquipment(List<Map<String, Object>> createdRows);
	
	int updateEquipment(List<Map<String, Object>> updatedRows);
	
	int deleteEquipment(List<String> idList);

	int checkDuplicateEquipCode(Map<String, Object> map);
}
