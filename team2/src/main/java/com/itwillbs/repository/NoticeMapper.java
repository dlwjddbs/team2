package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

	List<Map<String, Object>> getNoticeList(Map<String, Object> map);

//	Map<String, Object> getNoticeListMinMaxDate(String id);

	void createNotice(Map<String, Object> map);


		
	
	
	
}
