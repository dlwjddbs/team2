package com.itwillbs.controller;


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
	
	@PostMapping("/manufacture/checkDuplicateWorkcenterCode")
	@ResponseBody
	public Map<String, Object> checkDuplicateWorkcenterCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = manufactureService.checkDuplicateWorkcenterCode(map);
		
		return message;
	}
}


