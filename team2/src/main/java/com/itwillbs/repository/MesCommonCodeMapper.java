package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MesCommonCodeMapper {

	public List<Map<String, Object>> getGroupMesCommonCode(Map<String, Object> map);

}
