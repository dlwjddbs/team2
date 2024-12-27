package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.service.CmtService;
import com.itwillbs.service.HrManagementService;
import com.itwillbs.service.TestService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Controller
@RequiredArgsConstructor
@Log
public class CmtController {
	
	
    private final CmtService cmtService;

    
	@GetMapping("/cmt")
	public String cmt() {
		
		return "cmt";
		}

	@PostMapping("/cmt")
	public String cmtPro(@RequestParam Map<String, Object> map, Model model) {
		String id = "20241222";
		map.put("memberId", id);
		
//		Map<String, Object> checkIn = 
				cmtService.getCheckIn(map);
//		model.addAttribute("checkIn", checkIn);
		
		return "redirect:/cmt";
		}
	
	
}
