package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonCodeMapper {

	public List<Map<String, Object>> selectCommonCode();

	public List<Map<String, Object>> getGroupCommonCode(Map<String, Object> map);

	public int isDuplicateCommonCodeGroup(Map<String, Object> map);

	public int insertCommonCodeGroup(Map<String, Object> map);

	public int deleteCommonCodeGroup(Map<String, Object> map);

	public int updateCommonCodeGroup(Map<String, Object> map);

	public List<Map<String, Object>> getCommonCode(Map<String, Object> map);

	public int isDuplicateCommonCode(Map<String, Object> map);

	public int insertCommonCode(Map<String, Object> map);

	public int deleteCommonCode(Map<String, Object> map);

	public int updateCommonCode(Map<String, Object> map);

	public int isExistCommonCodeGroupChild(Map<String, Object> map);

}
