package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itwillbs.service.LotsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import oracle.jdbc.proxy.annotation.Post;


@Controller
@RequiredArgsConstructor
@Log
public class LotsController {
	
	private final LotsService lotsService;
	
	@GetMapping("/lots")
	public String lots(@AuthenticationPrincipal User user, Model model ) {
		if(user == null) {
			return "redirect://login";
		}
		return "/lots/lots";
	}
	
	@PostMapping("/ajax/lotHierarchy")
	@ResponseBody
	public List<Map<String, Object>> getLotHierarchy(@RequestBody Map<String, Object> map) throws JsonProcessingException {
		return lotsService.selectLotHierarchy(map);
	}
	
	@PostMapping("/ajax/lotDetailInfo")
	@ResponseBody
	public Map<String, Object> getLotDetailInfo(@RequestBody Map<String, Object> map) throws JsonProcessingException {
		
		return lotsService.selectLotDetailInfo(map);
	}
	
	
	
	
}
