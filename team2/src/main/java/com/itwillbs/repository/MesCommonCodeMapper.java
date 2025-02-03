package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MesCommonCodeMapper {

	public List<Map<String, Object>> getGroupMesCommonCode(Map<String, Object> map);

	public int isDuplicateMesCommonCodeGroup(Map<String, Object> map);

	public int isDuplicateMainOrder(Map<String, Object> map);

	public int insertMesCommonCodeGroup(Map<String, Object> map);

	public int isExistMesCommonCodeGroupChild(Map<String, Object> map);

	public int deleteMesCommonCodeGroup(Map<String, Object> map);

	public int updateMesCommonCodeGroup(Map<String, Object> map);

	public List<Map<String, Object>> getMesCommonCode(Map<String, Object> map);

	public int isDuplicateMesCommonCode(Map<String, Object> map);

	public int isDuplicateSubOrder(Map<String, Object> map);

	public int insertMesCommonCode(Map<String, Object> map);

	public int deleteMesCommonCode(Map<String, Object> map);

	public int updateMesCommonCode(Map<String, Object> map);




}
