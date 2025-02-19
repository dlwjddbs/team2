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

    // 출하 관리 페이지
    @GetMapping("/reqManage")
    public String getRequestList() {
        return "shipmentManagement/requestManage";
    }

    // 출하 정보 조회
    @GetMapping(URL)
    @ResponseBody
    public Map<String, Object> getRequest(
            @RequestParam(name = "requestId", required = false) String requestId,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "customerId", required = false) String customerId,
            @RequestParam(name = "itemId", required = false) String itemId,
            @RequestParam(name = "shipStatus", required = false) String shipStatus) {

        // 필터 조건을 서비스로 전달
        Map<String, Object> filters = new HashMap<>();
        filters.put("requestId", requestId);
        filters.put("startDate", startDate);
        filters.put("endDate", endDate);
        filters.put("customerId", customerId);
        filters.put("itemId", itemId);
        filters.put("shipStatus", shipStatus);

        // 필터링된 수주 데이터 조회
        Map<String, Object> reqData = shipService.getFilteredRequest(filters);

        log.info("조회된 수주 데이터: " + reqData);

        return reqData;
    }
    
    @PostMapping(URL + "/insert")
    public ResponseEntity<Map<String, Object>> insertRequest(@RequestBody List<Map<String, Object>> reqData) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 받은 데이터 처리
            shipService.insertRequest(reqData);
            response.put("success", true);
            response.put("message", "출하요청이 성공적으로 등록되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "출하요청 등록 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping(URL + "/update")
    public ResponseEntity<Map<String, Object>> updateRequest(@RequestBody Map<String, Object> reqData) {
    	Map<String, Object> response = new HashMap<>();
    	try {
    		shipService.updateRequest(reqData);
    		response.put("success", true);
    		response.put("message", "출하요청이 성공적으로 수정되었습니다.");
    		return ResponseEntity.ok(response);
    	} catch(Exception e) {
    		e.printStackTrace();
    		response.put("success", false);
            response.put("message", "출하요청 수정 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
    	}
    }
    
    @DeleteMapping(URL + "/delete")
    public ResponseEntity<Map<String, Object>> deleteRequest(@RequestBody List<String> requestIds) {
        Map<String, Object> response = new HashMap<>();
        try {
        	shipService.deleteRequest(requestIds);  
            response.put("success", true);
            response.put("message", "출하요청 정보가 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);  
        } catch (Exception e) {
            e.printStackTrace(); 
            response.put("success", false);
            response.put("message", "출하요청 삭제 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
        }
    }

}

