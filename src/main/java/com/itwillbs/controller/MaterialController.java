package com.itwillbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.MaterialService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class MaterialController {
	
	private final MaterialService materialService;
	
	@GetMapping("/material")
	public String material(@AuthenticationPrincipal User user) {
//		if (session.getAttribute("id") == null) {
//      return "redirect:/login"; 
//  }
		return "material/material";
	}
	
	@PostMapping("getMaterial")
	@ResponseBody
	public List<Map<String, Object>> getMaterialList(@RequestBody Map<String, Object> map) {
		List<Map<String, Object>> materialList = materialService.getMaterialList(map);
		System.out.println("받은 리스트 :" + map.toString());
		System.out.println("materialList: " + materialList);
		return materialList;
	}
	
	
	@PostMapping("/saveMaterial")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> saveMaterial(@AuthenticationPrincipal User user, @RequestBody List<Map<String, Object>> dataList) {
	    int result = 0;
//	    String id = user.getUsername();
	    
//	    List<Map<String, Object>> insertList = new ArrayList<>();
//	    List<Map<String, Object>> updateList = new ArrayList<>();
//	    List<Map<String, Object>> deleteList = new ArrayList<>();

	    for (Map<String, Object> data : dataList) {
	        String rowType = (String) data.get("rowType");
	        System.out.println("처리할 데이터: " + rowType + " → " + data); // rowType 출력
	        
	        if ("insert".equals(rowType)) {
	            result += materialService.insertMaterial(data);
	        } else if ("update".equals(rowType)) {
	        	result += materialService.updateMaterial(data);
	        } else if ("delete".equals(rowType)) {
	        	result += materialService.deleteMaterial(data);
	        }
	    }
	    
	    System.out.println("최종 저장된 데이터 개수: " + result); // 처리된 개수 출력
	 // JSON 형태로 응답 반환 (undefined 방지)
	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("status", "success");
	    responseMap.put("message", "저장이 완료되었습니다!");
	    responseMap.put("affectedRows", result);
	    
	    return ResponseEntity.ok(responseMap);
	}
	
	@PostMapping("/searchMaterial")
    public ResponseEntity<List<Map<String, Object>>> searchMaterial(@RequestBody Map<String, String> params) {
        String query = params.get("query");
        List<Map<String, Object>> result = materialService.searchMaterial(query);
        return ResponseEntity.ok(result);
    }
	
}
