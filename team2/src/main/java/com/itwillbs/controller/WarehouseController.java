package com.itwillbs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.WarehouseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class WarehouseController {
	
	private final WarehouseService warehouseService;
	
	@GetMapping("/whse")
	public String whse() {
		log.info("============= whse =============");
		return "/warehouse/whse";
	}
	
	@PostMapping("/warehouse/checkDuplicateWhseCode")
	@ResponseBody
	public Map<String, Object> checkDuplicateWhseCode(@RequestParam Map<String, Object> map) {
		log.info("============= checkDuplicateWhseCode =============");
		
		Map<String, Object> message = warehouseService.checkDuplicateWhseCode(map);
		
		return message;
		
	}
	
	@GetMapping("/whseLocation")
	public String process() {
		return "/warehouse/location";
	}
}
