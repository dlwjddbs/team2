package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonCodeMapper {

	public List<Map<String, Object>> selectCommonCode();

}
