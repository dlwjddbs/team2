package com.itwillbs.restController;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class ManufactureRestController {
	
	private final ManufactureService manufactureService;
	
	private final String workcenter_url = "/manufacture/workcenter";
	
	@GetMapping(workcenter_url)
	public Map<String, Object> getWorkcenter() {
		return manufactureService.selectWorkcenter();
	}	
	
	@PutMapping(workcenter_url)
	public Map<String, Object> modifyWorkcenter(@RequestBody Map<String, Object> requestData) {
		return manufactureService.modifyWorkcenter(requestData);
	}	
	
	@DeleteMapping(workcenter_url)
	public Map<String, Object> deletWorkcenter(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> workcenterIds = Arrays.asList(decodedIds.split(","));
        
        return manufactureService.deleteWorkcenter(workcenterIds);
	}	
	
}


