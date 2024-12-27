package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		log.info("=============salary history=============");
		model.addAttribute("salaryHistory", salaryHistory);
		return "/salary/salary_history";
	}
}
