package com.itwillbs.restController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.ClientInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class ClientInfoRestController {
	
	private final ClientInfoService clientInfoService;
	
	private final String clientinfo_url = "/clientinfo/client";
	
	@GetMapping(clientinfo_url)
	public Map<String, Object> getClientInfo() {
		return clientInfoService.selectClientInfo();
	}	
	
	@PutMapping(clientinfo_url)
	public Map<String, Object> modifyClientInfo(@RequestBody Map<String, Object> requestData) {
		return clientInfoService.modifyClientInfo(requestData);
	}	
	
	@DeleteMapping(clientinfo_url)
	public Map<String, Object> deleteClientInfo(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> clientinfoIds = Arrays.asList(decodedIds.split(","));
        
        return clientInfoService.deleteClientInfo(clientinfoIds);
	}	
}