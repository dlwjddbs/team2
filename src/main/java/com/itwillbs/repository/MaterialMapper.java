package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialMapper {

	List<Map<String, Object>> getMaterialList(Map<String, Object> map);

	int insertMaterial(Map<String, Object> data);

	int updateMaterial(Map<String, Object> data);

	int deleteMaterial(Map<String, Object> data);

	List<Map<String, Object>> searchMaterial(String query);

}
