package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InspectionMapper {
	List<Map<String, Object>> selectRejectionCode();

	List<Map<String, Object>> selectInboundList();

	List<Map<String, Object>> selectInboundDetail(Map<String, Object> map);

	int countExistingIds(List<Map<String, Object>> createdRows);

	int insertRejectionCode(List<Map<String, Object>> createdRows);

	int deleteRejectionCode(List<String> idList);

	int updateRejectionCode(List<Map<String, Object>> updatedRows);

	int insertInboundinspection(List<Map<String, Object>> rejectionList);
	
	// 입고 검수 불량 데이터 리스트
	List<Map<String, Object>> selectInboundInspectionList(Map<String, Object> map);

	int insertInboundInspection(@Param("rejectionList") List<Map<String, Object>> rejectionList);

	int deleteInboundInspection(@Param("poDetailId") String poDetailId);

	int insertInboundLots(Map<String, Object> map);

	int updatePoDetailStatus(Map<String, Object> map);

	int updatePurchaseOrderStatus(Map<String, Object> map);

	//------------------------
	
	List<Map<String, Object>> selectDefectCode(Map<String, Object> map);
	
	List<Map<String, Object>> selectDefectCauseCode(Map<String, Object> map);

	int insertProductionInspection(@Param("list")List<Map<String, Object>> list);

	int insertProductionLot(@Param("list")List<Map<String, Object>> list);
	
	int updateProductionOrderDetail(@Param("list")List<Map<String, Object>> list);

	int updateWortcenterLog(@Param("list")List<Map<String, Object>> list);

}
