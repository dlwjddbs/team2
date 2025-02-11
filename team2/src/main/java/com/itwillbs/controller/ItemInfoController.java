package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.service.ItemInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ItemInfoController {
	
	private final ItemInfoService itemInfoService;
	
	@GetMapping("/item")
	public String item() {
		log.info("============= item =============");
		return "/iteminfo/item";
	}
	
	@PostMapping("/iteminfo/checkDuplicateItemCode")
	@ResponseBody
	public Map<String, Object> checkDuplicateItemCode(@RequestParam Map<String, Object> map) {
		log.info("============= checkDuplicateItemCode =============");
		
		Map<String, Object> message = itemInfoService.checkDuplicateItemCode(map);
		
		return message;
	}
	
	// 공통코드 출력
	@PostMapping("/ajax/getMesCommonCode")
	@ResponseBody
	public List<Map<String, Object>> getMesCommonCode() throws JsonProcessingException {
		List<Map<String, Object>> data = itemInfoService.getMesCommonCode();
		
		ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		return data;
	}
	
}
