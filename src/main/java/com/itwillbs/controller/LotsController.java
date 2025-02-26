package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import com.itwillbs.service.LotsService;



@Controller
@RequiredArgsConstructor
@Log
public class LotsController {
	
	private final LotsService lotsService;
	
	@GetMapping("/lotTracking")
	public String lotTracking(@AuthenticationPrincipal User user, Model model ) {
		if(user == null) {
			return "redirect://login";
		}
		return "/lots/lots";
	}
	
	
	
	
	
}
