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
	
	@GetMapping("/mesCommonCode")
	public String commonCode(@AuthenticationPrincipal User user, HttpSession session) {
        if (user == null) {
            return "redirect:/login"; 
        }
		
		return "/commonCode/mes_com_code";
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
	
	@PostMapping("/system/deleteMesCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> deleteMesCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = mesCommonCodeService.deleteMesCommonCodeGroup(map);
		
		return message;
	}
	
	@PostMapping("/system/updateMesCommonCodeGroup")
	@ResponseBody
	public Map<String, Object> updateMesCommonCodeGroup(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = mesCommonCodeService.updateMesCommonCodeGroup(map);
		
		return message;
	}
	
	@PostMapping("/system/getMesCommonCode")
	@ResponseBody
	public List<Map<String, Object>> getMesCommonCode(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> codeList = mesCommonCodeService.getMesCommonCode(map);
		
		return codeList;
	}
	
	@PostMapping("/system/addMesCommonCode")
	@ResponseBody
	public Map<String, Object> addMesCommonCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = mesCommonCodeService.insertMesCommonCode(map);
		
		return message;
	}
	
	@PostMapping("/system/deleteMesCommonCode")
	@ResponseBody
	public Map<String, Object> deleteMesCommonCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = mesCommonCodeService.deleteMesCommonCode(map);
		
		return message;
	}
	
	@PostMapping("/system/updateMesCommonCode")
	@ResponseBody
	public Map<String, Object> updateMesCommonCode(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = mesCommonCodeService.updateMesCommonCode(map);
		
		return message;
	}
	
}

