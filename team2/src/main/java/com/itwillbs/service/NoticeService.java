package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.HashMap;
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
	
//	작성
	public void createNotice(Map<String, Object> map) {
		noticeMapper.createNotice(map);		
	}
// 상세
	public Map<String, Object> getNoticeDetail(Map<String, Object> map) {
		Map<String, Object> data = noticeMapper.detailNotice(map);
		map.put("createTime", new Timestamp(System.currentTimeMillis()));
		return data;
	}
//	수정
	public void updateNotice(Map<String, Object> map) {
		map.put("createTime", new Timestamp(System.currentTimeMillis()));
		noticeMapper.updateNotice(map);
	}
// 삭제
	public void deleteNotice(Map<String, Object> map) {
		noticeMapper.deleteNotice(map);
	}
}
	

