package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.service.MesCommonCodeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class MesCommonCodeController {
	
	private final MesCommonCodeService mesCommonCodeService;
	
	@GetMapping("/admin/mesCommonCode")
	public String commonCode(@AuthenticationPrincipal User user, HttpSession session) {
        if (user == null) {
            return "redirect:/login"; 
        }
		
		return "/commonCode/common_code2";
	}
	
	@PostMapping("/system/getGroupMesCommonCode")
	@ResponseBody
	public List<Map<String, Object>> getGroupCommonCode(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> codeList = mesCommonCodeService.getGroupMesCommonCode(map);
		
		return codeList;
	}
	
	@PostMapping("/system/addMesCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> addMesCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = mesCommonCodeService.insertMesCommonCodeGroup(map);
		
		return message;
	}
	
}

