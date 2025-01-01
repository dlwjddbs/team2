package com.itwillbs.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
	
	@PostMapping("/addMember")
	public String addMember(@RequestParam Map<String, Object> param, Model model) {
		
//		<< null 또는 "" 값 제거 >>
		param.entrySet().removeIf(entry -> 
	        entry.getValue() == null || (entry.getValue() instanceof String && ((String) entry.getValue()).isEmpty())
	    );
		
//		<< EAMIL 추가 >>
		if(!ObjectUtils.isEmpty(param.get("EMAIL_ID"))) {
			String email = param.get("EMAIL_ID") + "@" + param.get("EMAIL_DOMAIN");
			
			param.remove("EMAIL_ID");
			param.remove("EMAIL_DOMAIN");
			
			param.put("EMAIL", email);
		} 
		
//		<< CREATE_DATE 추가 >>
		LocalDateTime now = LocalDateTime.now();
		String create_date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		param.put("CREATE_DATE", create_date);
		
		
		int insertCount = hrManagementService.addMember(param);
		
		if(insertCount > 0) {
			System.out.println("========insert 성공");
			System.out.println("insertCount: " + insertCount);
			
			Object val = param.get("ID");
			
			System.out.println("id 타입: " + val.getClass().getName());
//			hrManagementService.addHistory(param, "GRADE_HISTORY");
//			hrManagementService.addHistory(param, "DEPARTMENT_HISTORY");
		}
		
		
		return "redirect:/memberList";
	}
//======================================================================================================
	
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
