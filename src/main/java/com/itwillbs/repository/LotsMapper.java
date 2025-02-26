package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LotsMapper {

	List<Map<String, Object>> selectLots();

	List<Map<String, Object>> selectLotHierarchy(Map<String, Object> map);

	List<Map<String, Object>> selectLotDetailInfo(Map<String, Object> map);

	List<Map<String, Object>> selectMaterialInfo(Map<String, Object> map);

	List<Map<String, Object>> selectInboundInspection(Map<String, Object> map);


}
