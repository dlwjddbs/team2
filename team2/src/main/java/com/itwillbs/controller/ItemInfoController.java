package com.itwillbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.EquipmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class ItemInfoController {
	
//	private final ItemInfoService itemInfoService;
	
	@GetMapping("/item")
	public String equip() {
		return "/iteminfo/item";
	}
	
}
