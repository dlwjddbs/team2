package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
	// 신규 사원 추가
	@PostMapping("/ajax/addMember")
	public String addMember(@RequestParam Map<String, Object> param, Model model) {
		
		int insertCount = hrManagementService.addMember(param);
		
		return "redirect:/appoint/memberList";
	}
//======================================================================================================
	
	// 사원 상세 정보 수정
	@PostMapping("/ajax/updateMember")
	public String updateMember(@RequestParam Map<String, Object> param) {
		
		int updateCount = hrManagementService.updateMember(param);
		
		// update 판별 추가 예정
		
		return "redirect:/appoint/memberList";
	}
	
	// 사원 정보
	@GetMapping("/appoint/memberList")
	public String memberList(@AuthenticationPrincipal User user, Model model) {
		if (user == null) {
            return "redirect:/login"; 
        }
    	
		return "/HRManagement/member_list";
	}
	
	
	// 사원 정보
	@PostMapping("/ajax/getMemberList")
	@ResponseBody
	public List<Map<String, Object>> getMemberList(@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> memberList = hrManagementService.getMemberList();
		return memberList;
	}	
	
	// 사원 상세 정보
	@PostMapping("/ajax/getMemberDetail")
	@ResponseBody
	public Map<String, Object> getMemberDetail(@RequestParam Map<String, Object> param) throws JsonProcessingException {
		Map<String, Object> member = hrManagementService.getMemberDetail(param);
		return member;
	}

	
	
	// 은행코드, 부서, 직급
	@PostMapping("/ajax/getOrganizationData")
	@ResponseBody
	public List<Map<String, Object>> getOrganizationData() throws JsonProcessingException {
		List<Map<String, Object>> data = hrManagementService.getOrganizationData();
		
		ObjectMapper mapper = new ObjectMapper();
		String dataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		return data;
	}
	
}
