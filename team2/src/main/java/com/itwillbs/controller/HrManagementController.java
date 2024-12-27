package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		log.info("=============Meber List=============");
		return "/HRManagement/member_list";
	}
	
	
	// 맴버 정보
	@PostMapping("/getMemberList")
	@ResponseBody
	public List<Map<String, Object>> getMemberList(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> memberList = hrManagementService.getMemberList();
		return memberList;
	}	
	
	
	// 은행코드, 부서, 직급
	@PostMapping("/getOrganizationData")
	@ResponseBody
	public List<Map<String, Object>> getOrganizationData(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> data = hrManagementService.getOrganizationData();
		log.info(data.toString());
		return data;
	}	
	
}
