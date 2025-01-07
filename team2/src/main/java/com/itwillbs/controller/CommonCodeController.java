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
import com.itwillbs.service.CommonCodeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class CommonCodeController {
	
	private final CommonCodeService commonCodeService;
	
	@GetMapping("/commonCode")
	public String commonCode(HttpSession session) {
//		if (session.getAttribute("id") == null) {
//            return "redirect:/login"; 
//        }
		
		return "/commonCode/common_code";
	}
	
//	@PostMapping("getCommonCode")
//	@ResponseBody
//	public List<Map<String, Object>> getCommonCode() throws JsonProcessingException  {
//		List<Map<String, Object>> Code = commonCodeService.getCommonCodes();
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Code);
//		
//		return Code;
//	}
	
	@PostMapping("getGroupCommonCode")
	@ResponseBody
	public List<Map<String, Object>> getGroupCommonCode(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> Code = commonCodeService.getGroupCommonCode(map);
		
		return Code;
	}
	
	@PostMapping("addCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> addCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.insertCommonCodeGroup(map);
		
		return message;
	}
	
	@PostMapping("/deleteCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> deleteCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.deleteCommonCodeGroup(map);
		
		return message;
	}
	
	@PostMapping("/updateCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> updateCommonCodeGroup(HttpSession session, @RequestParam Map<String, Object> map) {
		Map<String, Object> message = commonCodeService.updateCommonCodeGroup(map);
		
		return message;
	}
	
}

