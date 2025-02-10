package com.itwillbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.EquipmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ClientController {
	
//	private final ClientService ClientService;
	
	@GetMapping("/client")
	public String client() {
		log.info("============= item =============");
		return "/clientinfo/client";
	}
	
}
