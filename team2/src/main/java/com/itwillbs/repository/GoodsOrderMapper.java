package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsOrderMapper {

	List<Map<String, Object>> getGoodsList(Map<String, Object> map);

	List<Map<String, Object>> getGoodsPoList(Map<String, Object> requestData);

}
