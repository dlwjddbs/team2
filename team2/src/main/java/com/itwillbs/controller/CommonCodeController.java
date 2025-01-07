package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.service.CommonCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class CommonCodeController {
	
	private final CommonCodeService commonCodeService;
	
	@GetMapping("/commonCode")
	public String commonCode() {
		return "/commonCode/common_code";
	}
	
	@PostMapping("getCommonCode")
	@ResponseBody
	public List<Map<String, Object>> getCommonCode() throws JsonProcessingException  {
		List<Map<String, Object>> Code = commonCodeService.getCommonCodes();
		
		ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Code);
		
		return Code;
	}
	
}

