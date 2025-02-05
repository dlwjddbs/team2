package com.itwillbs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.EquipmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class EquipmentController {
	
	private final EquipmentService equipmentService;
	
	@GetMapping("/equip")
	public String equip() {
		log.info("============= equip =============");
		return "/equipment/equip";
	}
	
	@PostMapping("/equipment/checkDuplicateEquipCode")
	@ResponseBody
	public Map<String, Object> checkDuplicateEquipCode(@RequestParam Map<String, Object> map) {
		log.info("============= checkDuplicateEquipCode =============");
		
		Map<String, Object> message = equipmentService.checkDuplicateEquipCode(map);
		
		return message;
	}
}
