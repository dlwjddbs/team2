package com.itwillbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.BomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class BomController {
	
	private final BomService bomService;
	
	@GetMapping("/bom")
	public String bom() {
		return "/manufacture/bom";
	}
	
}


