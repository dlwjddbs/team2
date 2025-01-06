package com.itwillbs.controller;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.HrManagementService;
import com.itwillbs.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
@Log
public class HrManagementController {
	
	private final HrManagementService hrManagementService;
	
	@PostMapping("/addMember")
	public String addMember(@RequestParam Map<String, Object> param, Model model) {
		
		int insertCount = hrManagementService.addMember(param);
		
		
		
		/*		
		if(insertCount > 0) {
			System.out.println("========insert 성공");
			System.out.println("insertCount: " + insertCount);
			
			Object val = param.get("ID");
			
			System.out.println("id 타입: " + val.getClass().getName());
//			hrManagementService.addHistory(param, "GRADE_HISTORY");
//			hrManagementService.addHistory(param, "DEPARTMENT_HISTORY");
		}
		*/
		
		return "redirect:/memberList";
	}
//======================================================================================================
	
	// 사원 상세 정보 수정
	@PostMapping("/updateMember")
	public String updateMember(@RequestParam Map<String, Object> param) {
		System.out.println("====================== 사원 상세 정보 수정 ======================");
		System.out.println(param);
		
		int updateCount = hrManagementService.updateMember(param);
		
		
		return "redirect:/memberList";
	}
	
	// 사원 정보
	@GetMapping("/memberList")
	public String memberList(Map<String, Object> map, Model model) {
		log.info("=============Meber List=============");
		return "/HRManagement/member_list";
	}
	
	
	// 사원 정보
	@PostMapping("/getMemberList")
	@ResponseBody
	public List<Map<String, Object>> getMemberList(@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> memberList = hrManagementService.getMemberList();
		log.info(memberList.toString());
		return memberList;
	}	
	
	// 사원 상세 정보
	@PostMapping("/getMemberDetail")
	@ResponseBody
	public Map<String, Object> getMemberDetail(@RequestParam Map<String, Object> param) throws JsonProcessingException {
		System.out.println("====================== 사원 상세 정보 ======================");
		System.out.println(param);
		Map<String, Object> member = hrManagementService.getMemberDetail(param);
		
		/*
		ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(member);
		*/
		return member;
	}

	
	
	// 은행코드, 부서, 직급
	@PostMapping("/getOrganizationData")
	@ResponseBody
	public List<Map<String, Object>> getOrganizationData() throws JsonProcessingException {
		List<Map<String, Object>> data = hrManagementService.getOrganizationData();
		
		ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		return data;
	}
	
}
