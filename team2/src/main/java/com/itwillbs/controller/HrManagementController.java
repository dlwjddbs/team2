package com.itwillbs.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		
//		String email = param.get("email_id").toString() + "@" + param.get("email_domain").toString();
		
		LocalDateTime now = LocalDateTime.now();
		String CREATE_DATE = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
//======================================================================================================
		
//		param.put("email", email);
		param.put("CREATE_DATE", CREATE_DATE);
		
		System.out.println(param.toString());
		int insertCount = hrManagementService.addMember(param);
		System.out.println(insertCount);
//		if(insertCount > 0) {
//			hrManagementService.addHistory(param, "GRADE_HISTORY");
//			hrManagementService.addHistory(param, "DEPARTMENT_HISTORY");
//		}
		
		return "redirect:/memberList";
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
		log.info(memberList.toString());
		return memberList;
	}	
	
	
	// 은행코드, 부서, 직급
	@PostMapping("/getOrganizationData")
	@ResponseBody
	public Map<String, Object> getOrganizationData(@RequestParam Map<String, Object> map) throws JsonProcessingException {
		Map<String, Object> data = hrManagementService.getOrganizationData();
		
		ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		return data;
	}	
	
}
