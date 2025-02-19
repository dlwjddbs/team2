package com.itwillbs.controller;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ManufacturePostController {
	
	private final ManufactureService manufactureService;
	
	@PostMapping("/manufacture/checkDuplicateCode")
	@ResponseBody
	public Map<String, Object> checkDuplicateCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = manufactureService.checkDuplicateCode(map);
		
		return message;
	}
	
}


