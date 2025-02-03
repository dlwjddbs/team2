package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {
	
	List<Map<String, Object>> getPurchaseList(Map<String, Object> map);

	int insertPurchase(Map<String, Object> data);

	int updatePurchase(Map<String, Object> data);

	int deletePurchase(Map<String, Object> data);
}
