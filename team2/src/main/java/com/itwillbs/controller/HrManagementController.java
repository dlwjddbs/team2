package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.repository.TestMapper;
import com.itwillbs.service.HrManagementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class HrManagementController {
	
	private final HrManagementService hrManagementService;
	
	// MyBatis흐름
		// Controller -> Service -> Mapper.java -> Mapper.xml
		
	@PostMapping("/addMember")
	public String addMember(@RequestParam Map<String, Object> param, Model model) {
		log.info("=============Add Member=============");
		log.info(param.toString());
		return "/HRManagement/member_list";
	}
	
	@GetMapping("/memberList")
	public String memberList(Map<String, Object> map, Model model) {
		List<Map<String, Object>> memberList = hrManagementService.getMemberList();
		log.info("=============Meber List=============");
		model.addAttribute("memberList", memberList);
		return "/HRManagement/member_list";
	}
	
}
