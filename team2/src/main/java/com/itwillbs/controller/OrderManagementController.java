package com.itwillbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderManagementController {

    private final OrderService orderService;

    private final String URL = "/ajax/order";

    // 수주 관리 페이지
    @GetMapping("/order/orderManage")
    public String getOrderList() {
        return "orderManagement/orderManage";
    }

    // 수주 정보 조회
    @GetMapping(URL)
    @ResponseBody
    public Map<String, Object> getOrder(@RequestParam(name= "orderId", required = false) String orderId) {
        // orderId가 없으면 전체 수주, 있으면 해당 수주만 조회
        Map<String, Object> orderData = orderService.getOrder(orderId);  // 주문 번호에 따라 데이터를 조회
        log.info("조회된 수주 데이터: " + orderData);

        return orderData;
    }
    
    @DeleteMapping(URL + "/delete")
    public ResponseEntity<Map<String, Object>> deleteOrder(@RequestBody List<String> orderIds) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 수주 삭제 처리
            orderService.deleteOrder(orderIds);  
            response.put("success", true);
            response.put("message", "수주정보가 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);  // 성공 시 JSON 응답
        } catch (Exception e) {
            e.printStackTrace();  // 오류 추적을 위한 로그 출력
            response.put("success", false);
            response.put("message", "수주정보 삭제 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // 실패 시 JSON 응답
        }
    }

}

