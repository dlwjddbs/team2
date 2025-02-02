package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {
	
	List<Map<String, Object>> getPurchase(Map<String, Object> map);
}
