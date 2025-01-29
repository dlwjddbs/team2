package com.itwillbs.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Controller
@RequiredArgsConstructor
@Log
public class PopController {
	
	@GetMapping("/workOrder")
	public String lots(@AuthenticationPrincipal User user, Model model ) {
		if(user == null) {
			return "redirect://login";
		}
		
		return "/POP/workOrder";
	}
	
}
