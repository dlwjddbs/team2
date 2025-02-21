package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductionMapper {
	
	List<Map<String, Object>> selectWorkcenter(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectProductionOrderDetail(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectProductionOrderDetailBom(Map<String, Object> requestData);

}
