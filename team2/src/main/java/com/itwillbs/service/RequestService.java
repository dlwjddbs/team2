package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.RequestMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestService {

    private final RequestMapper reqMapper;

    // 수주 조회 (orderId가 있으면 상세 조회, 없으면 전체 조회, 필터링 조건)
    public Map<String, Object> getFilteredRequest(Map<String, Object> filters) {
        // 필터 조건에 맞는 수주 데이터를 조회
        List<Map<String, Object>> reqData = reqMapper.selectFilteredRequest(filters);
        
        // 결과를 response에 담아 반환
        Map<String, Object> response = new HashMap<>();
        response.put("reqs", reqData);
        return response;
    }

	public void insertRequest(Map<String, Object> reqData) {
		reqMapper.insertRequest(reqData);
	}
	
	public void updateRequest(Map<String, Object> reqData) {
		reqMapper.updateRequest(reqData);
	}
	
    public void deleteRequest(List<String> reqIds) {
    	reqMapper.deleteRequest(reqIds);
    }

}
