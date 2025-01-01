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

import com.itwillbs.service.SalaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class SalaryController {
	
	private final SalaryService salaryService;
	
	// MyBatis흐름
	// Controller -> Service -> Mapper.java -> Mapper.xml
	
	@GetMapping("/salaryHistory")
	public String salaryhistory(Map<String, Object> map, Model model) {
		List<Map<String, Object>> salaryHistory = salaryService.getSalaryList();
		model.addAttribute("salaryHistory", salaryHistory);
		Map<String, Object> salaryHistoryMinMaxDate = salaryService.getSalaryHistoryMinMaxDate();
		model.addAttribute("salaryHistoryMinMaxDate", salaryHistoryMinMaxDate);
		return "/salary/salaryHistory";
	}
	
	@PostMapping("/getSalaryHistory")
	@ResponseBody
	public List<Map<String, Object>> getSalaryHistory(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> allSalaryHistory = salaryService.getSalaryHistory(map); 
		return allSalaryHistory;
	}
	
//	급여입력(관리자)
	
	@GetMapping("/salaryInput")
	public String salaryInput() {
		return "/salary/salaryInput";
	}
	
//	@PostMapping("/salaryInput")
//	public 
	
	@PostMapping("/writeSalary")
	public String addMember(@RequestParam Map<String, Object> param, Model model) {
		
		log.info("=============write salary=============");
		
//		String email = param.get("email_id").toString() + "@" + param.get("email_domain").toString();
		
		LocalDateTime now = LocalDateTime.now();
		String CREATE_DATE = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
//======================================================================================================
		
//		param.put("email", email);
		param.put("CREATE_DATE", CREATE_DATE);
		
		System.out.println(param.toString());
		int insertCount = salaryService.writeSalary(param);
		System.out.println(insertCount);
//		if(insertCount > 0) {
//			salaryService.addHistory(param, "GRADE_HISTORY");
//			salaryService.addHistory(param, "DEPARTMENT_HISTORY");
//		}
		
		return "redirect:/salaryInput";
	}	
	
	// 급여 입력 정보
	// 맴버 정보
	@PostMapping("/memberSalaryList")
	@ResponseBody
	public List<Map<String, Object>> memberSalaryList(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> salaryList = salaryService.memberSalaryList();
		log.info(salaryList.toString());
		return salaryList;
	}	
}
