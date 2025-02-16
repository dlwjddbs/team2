package com.itwillbs.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ManufactureController {
	
	private final ManufactureService manufactureService;
	
	@GetMapping("/workcenter")
	public String workcenter() {
		return "/manufacture/workcenter";
	}
	
	@PostMapping("/manufacture/checkDuplicateCode")
	@ResponseBody
	public Map<String, Object> checkDuplicateCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = manufactureService.checkDuplicateCode(map);
		
		return message;
	}
	
	@GetMapping("/process")
	public String process() {
		return "/manufacture/process";
	}
	
	@GetMapping("/routing")
	public String routing() {
		return "/manufacture/routing";
	}
	
	@GetMapping("/productionOrder")
	public String productionOrder() {
		return "/manufacture/productionOrder";
	}
	
}


