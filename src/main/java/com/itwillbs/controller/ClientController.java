package com.itwillbs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.ClientInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ClientController {
	
	private final ClientInfoService clientInfoService;
	
	@GetMapping("/client")
	public String client() {
		log.info("============= item =============");
		return "/clientinfo/client";
	}
	
	@PostMapping("/clientinfo/checkDuplicateClientCode")
	@ResponseBody
	public Map<String, Object> checkDuplicateClientCode(@RequestParam Map<String, Object> map) {
		log.info("============= checkDuplicateClientCode =============");
		
		Map<String, Object> message = clientInfoService.checkDuplicateClientCode(map);
		
		return message;
	}	
	
}
