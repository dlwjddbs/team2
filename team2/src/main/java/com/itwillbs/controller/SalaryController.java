package com.itwillbs.controller;

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
}
