package com.itwillbs.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.itwillbs.service.InspectionService;
import com.itwillbs.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Controller
@RequiredArgsConstructor
@Log
public class PopController {
	
	private final InspectionService inspectionService;
	
	
	@GetMapping("/workOrder")
	public String lots(@AuthenticationPrincipal User user, Model model ) {
		if(user == null) {
			return "redirect://login";
		}
		
		return "/POP/workOrder";
	}
	@GetMapping("/inboundOrder")
	public String inboundOrder(@AuthenticationPrincipal User user, Model model ) {
		if(user == null) {
			return "redirect://login";
		}
		return "/POP/inbound/inboundOrder";
	}
	
	@PostMapping("/ajax/saveInboundInspection")
	public String saveInboundInspection(@RequestBody List<Map<String, Object>> rejectionList) {
		inspectionService.processInboundInspection(rejectionList);
	    return "redirect:/inboundOrder";
	}

	
	
}
