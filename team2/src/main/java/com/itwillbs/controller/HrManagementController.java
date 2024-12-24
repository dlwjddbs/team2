package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	@GetMapping("/memberList")
	public String memberList(Map<String, Object> map, Model model) {
		List<Map<String, Object>> memberList = hrManagementService.getmemberList();
		log.info("=============Controller List=============");
		log.info(memberList.toString());
		model.addAttribute("memberList", memberList);
		return "/HRManagement/member_list";
	}
	
//	@PostMapping("path")
//	public String postMethodName(@RequestBody String entity) {
//		//TODO: process POST request
//		
//		return entity;
//	}
	
}
