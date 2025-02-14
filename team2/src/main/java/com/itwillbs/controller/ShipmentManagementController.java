package com.itwillbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.RequestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShipmentManagementController {

    private final RequestService shipService;

    private final String URL = "/ajax/req";

    // 수주 관리 페이지
    @GetMapping("/reqManage")
    public String getRequestList() {
        return "shipmentManagement/requestManage";
    }

    // 수주 정보 조회
    @GetMapping(URL)
    @ResponseBody
    public Map<String, Object> getRequest(
            @RequestParam(name = "requestId", required = false) String requestId,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "customerId", required = false) String customerId,
            @RequestParam(name = "itemId", required = false) String itemId,
            @RequestParam(name = "orderStatus", required = false) String orderStatus) {

        // 필터 조건을 서비스로 전달
        Map<String, Object> filters = new HashMap<>();
        filters.put("requestId", requestId);
        filters.put("startDate", startDate);
        filters.put("endDate", endDate);
        filters.put("customerId", customerId);
        filters.put("itemId", itemId);
        filters.put("orderStatus", orderStatus);

        // 필터링된 수주 데이터 조회
        Map<String, Object> reqData = shipService.getFilteredRequest(filters);

        log.info("조회된 수주 데이터: " + reqData);

        return reqData;
    }
    
    @PostMapping(URL + "/insert")
    public ResponseEntity<Map<String, Object>> insertRequest(@RequestBody Map<String, Object> reqData) {
        Map<String, Object> response = new HashMap<>();
        try {
            // insertOrder 메서드를 호출하여 새 수주 정보를 DB에 추가
        	shipService.insertRequest(reqData);  
            response.put("success", true);
            response.put("message", "수주정보가 성공적으로 등록되었습니다.");
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "수주정보 등록 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
        }
    }


    @PostMapping(URL + "/update")
    public ResponseEntity<Map<String, Object>> updateRequest(@RequestBody Map<String, Object> reqData) {
    	Map<String, Object> response = new HashMap<>();
    	try {
    		shipService.updateRequest(reqData);
    		response.put("success", true);
    		response.put("message", "수주정보가 성공적으로 수정되었습니다.");
    		return ResponseEntity.ok(response);
    	} catch(Exception e) {
    		e.printStackTrace();
    		response.put("success", false);
            response.put("message", "수주정보 수정 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
    	}
    }
    
    @DeleteMapping(URL + "/delete")
    public ResponseEntity<Map<String, Object>> deleteRequest(@RequestBody List<String> requestIds) {
        Map<String, Object> response = new HashMap<>();
        try {
        	shipService.deleteRequest(requestIds);  
            response.put("success", true);
            response.put("message", "수주정보가 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);  
        } catch (Exception e) {
            e.printStackTrace(); 
            response.put("success", false);
            response.put("message", "수주정보 삭제 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
        }
    }

}

