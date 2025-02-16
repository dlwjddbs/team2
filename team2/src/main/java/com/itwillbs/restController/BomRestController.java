package com.itwillbs.restController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.BomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class BomRestController {
	
	private final BomService bomService;
	
	private final String bom_url = "/manufacture/bom";
	private final String bomDetail_url = "/manufacture/bomDetail";
	private final String component_url = "/manufacture/bomDetail_Component";
	
	@GetMapping(bom_url)
	public Map<String, Object> getBom() {
		return bomService.selectBom();
	}	
	
	@PutMapping(bom_url)
	public Map<String, Object> modifyBom(@RequestBody Map<String, Object> requestData) {
		return bomService.modifyBom(requestData);
	}	
	
	@DeleteMapping(bom_url)
	public Map<String, Object> deleteBom(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> bomIds = Arrays.asList(decodedIds.split(","));
        
        return bomService.deleteBom(bomIds);
	}
	
	@GetMapping(bomDetail_url)
	public Map<String, Object> getBomDetail(@RequestParam Map<String, Object> requestData) {
		return bomService.selectBomDetail(requestData);
	}
	
	@PostMapping(bomDetail_url)
	public Map<String, Object> addBomDetail(@RequestBody Map<String, Object> requestData) {
	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		
        return bomService.addBomDetail(createdRows);
	}
	
	@DeleteMapping(bomDetail_url)
	public Map<String, Object> deletBomDetail(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> bomDetailIds = Arrays.asList(decodedIds.split(","));
	    
        return bomService.deleteBomDetail(bomDetailIds);
	}
	
	@GetMapping(component_url)
	public Map<String, Object> getComponent(@RequestParam Map<String, Object> requestData) {
		return bomService.selectItemAndMaterial(requestData);
	}
}


