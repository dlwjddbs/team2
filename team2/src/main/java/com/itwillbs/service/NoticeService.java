package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeMapper noticeMapper;
	
//	리스트 조회
	public List<Map<String, Object>> getNoticeList(Map<String, Object> map) {
		map.put("createTime", new Timestamp(System.currentTimeMillis()));
		return noticeMapper.getNoticeList(map);
	}
	
//	public Map<String, Object> getNoticeListMinMaxDate(String id) {
//
//		return noticeMapper.getNoticeListMinMaxDate(id);
//	}
	
//	작성
	public void createNotice(Map<String, Object> map) {
		noticeMapper.createNotice(map);
		
	}

	
	 
}
	

