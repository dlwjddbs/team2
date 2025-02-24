package com.itwillbs.restController;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.InspectionService;
import com.itwillbs.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class InspectionRestController {
	
	private final InspectionService inspectionService;
	
	@GetMapping("/inspection/inboundList")
	public Map<String, Object> getInboundList() {
		return inspectionService.selectInboundList();
	}	
	
	@GetMapping("/inspection/inboundDetail")
	public Map<String, Object> getInboundDetail(@RequestParam Map<String, Object> map) {
		return inspectionService.selectInboundDetail(map); 
	}
	
//======================================================================	
	
//	입고 검수 반려 코드
	private final String rejection_url = "/toast/rejectionCode";
	@GetMapping(rejection_url)
	public Map<String, Object> getRejectionCode() {
		return inspectionService.selectRejectionCode();
	}
	
	@PostMapping(rejection_url)
	public Map<String, Object> insertRejectionCode(@RequestBody Map<String, Object> requestData) {
	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
        return inspectionService.insertRejectionCode(createdRows);
	}	
	
	@DeleteMapping(rejection_url)
	public Map<String, Object> deleteRejectionCode(@RequestHeader("X-Delete-IDs") String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        return inspectionService.deleteRejectionCode(idList);
	}	
	
	@PutMapping(rejection_url)
	public Map<String, Object> updateRejectionCode(@RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		return inspectionService.updateRejectionCode(updatedRows);
	}	
	
	
	//===================================================================
	
	@GetMapping("/inspection/inboundInspection")
	public Map<String, Object> getInboundInspectionList(@RequestParam Map<String, Object> map) {
		Map<String, Object> data = inspectionService.selectInboundInspectionList(map);
		return data;
	}
	
	
	@GetMapping("/inspection/rejectionCode")
	public Map<String, Object> getrejectionCode(@RequestParam Map<String, Object> map) {
		return inspectionService.selectRejectionCode();
	}
	
	@GetMapping("/inspection/defectCode")
	public Map<String, Object> getDefectCode(@RequestParam Map<String, Object> map) {
		return inspectionService.selectDefectCode(map);
	}
	
	@GetMapping("/inspection/defectCauseCode")
	public Map<String, Object> getDefectCauseCode(@RequestParam Map<String, Object> map) {
		return inspectionService.selectDefectCauseCode(map);
	}
	
	
}

