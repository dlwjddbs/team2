package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {

	List<Map<String, Object>> selectMenu(String auth);

	List<Map<String, Object>> getMenuList(Map<String, Object> map);

	int insertMenu(Map<String, Object> map);

	int isDuplicateMenuSortOrder(Map<String, Object> map);

	int isDuplicateMenuURL(Map<String, Object> map);

	int isExistChildMenu(Map<String, Object> map);

	int updateMenu(Map<String, Object> map);
	
}
