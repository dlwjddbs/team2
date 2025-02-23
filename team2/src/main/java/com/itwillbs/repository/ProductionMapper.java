package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductionMapper {
	
	List<Map<String, Object>> selectWorkcenter(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectProductionOrderDetail(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectProductionOrderDetailBom(Map<String, Object> requestData);
	
	List<Map<String, Object>> selectStock(Map<String, Object> requestData);

	int insertMaterialsDetail(List<Map<String, Object>> createdRows);

	int selectMaxPutMaterialsDetailId();

	int insertWorkcenterLog(Map<String, Object> map);

	int updateLotQuantity(List<Map<String, Object>> createdRows);

	int updateWarehouseItemQuantity(List<Map<String, Object>> createdRows);

	int updateProductionOrderDetailStatus(Map<String, Object> first);

	int updateProductionOrderStatus(Map<String, Object> first);

}
