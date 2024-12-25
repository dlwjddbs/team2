package com.itwillbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Controller
@RequiredArgsConstructor
@Log
public class CmtController {
	
	@GetMapping("/cmt")
	public String cmt() {
		return "cmt";
	}
	
	
}
