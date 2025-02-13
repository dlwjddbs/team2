package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsOrderMapper {

	List<Map<String, Object>> getGoodsList(Map<String, Object> map);

	List<Map<String, Object>> getGoodsPoList(Map<String, Object> requestData);

	int insertGoods(Map<String, Object> data);

	int updateGoods(Map<String, Object> data);

	int deleteGoods(Map<String, Object> data);

	List<Map<String, Object>> getGoodsDetail(Map<String, Object> map);

	int updateDetail(Map<String, Object> data);

	int deletePurchase(Map<String, Object> data);

	int insertGoDetail(Map<String, Object> data);

}
