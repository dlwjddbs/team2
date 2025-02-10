package com.itwillbs.restController;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.ClientInfoService;
import com.itwillbs.service.EquipmentService;
import com.itwillbs.service.ItemInfoService;

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
	
//	@PostMapping(iteminfo_url)
//	public Map<String, Object> addItemInfo(@RequestBody Map<String, Object> requestData) {
//	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
//		
//        return itemInfoService.addItemInfo(createdRows);
//	}
}