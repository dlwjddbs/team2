package com.itwillbs.service;

import java.util.ArrayList;
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

    public void insertRequest(List<Map<String, Object>> reqData) {
        try {
            List<Map<String, Object>> insertDataList = new ArrayList<>();

            for (Map<String, Object> item : reqData) {
                // 로그로 각 항목 출력
                System.out.println("Processing item: " + item);

                String createDate = (String) item.get("createDate");
                String orderId = (String) item.get("orderId");
                String remarks = (String) item.get("remarks");
                String registBy = (String) item.get("registBy");

                Map<String, Object> requestData = new HashMap<>();
                requestData.put("CREATE_DATE", createDate);
                requestData.put("ORDER_ID", orderId);
                requestData.put("REMARKS", remarks);
                requestData.put("REGIST_BY", registBy);

                insertDataList.add(requestData);
            }

            // 리스트 출력
            System.out.println("Data to be inserted: " + insertDataList);

            reqMapper.insertRequest(insertDataList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("출하요청 등록 중 오류가 발생했습니다.", e);
        }
    }

	
	public void updateRequest(Map<String, Object> reqData) {
		reqMapper.updateRequest(reqData);
	}
	
    public void deleteRequest(List<String> reqIds) {
    	reqMapper.deleteRequest(reqIds);
    }

}
