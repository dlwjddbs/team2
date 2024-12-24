package com.itwillbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class TestController {
	
	private final TestService testService;
	
	// MyBatis흐름
	// Controller -> Service -> Mapper.java -> Mapper.xml
	@GetMapping("/testMyBatis")
	public String testMyBatis() {
		System.out.println(testService.getTest());
		return "index";
	}
	
	@GetMapping("/advanced")
	public String advanced() {
		return "/attendance/advanced";
	}
	
	@GetMapping("/data")
	public String data() {
		return "data";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/modals")
	public String modals() {
		return "modals";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
}


