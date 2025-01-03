package com.itwillbs.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
	
	// 사원별 급여조회 - 추가 예정
	
	// 관리자 급여입력 내역 조회
	// 페이지 첫 로딩 시 입력된 급여 테이블 출력
	@GetMapping("/salaryInput")
	public String salaryInput(Map<String, Object> map, Model model) {
		log.info("============= salaryInput =============");
		
		String id = "admin";
		Map<String, Object> salaryInputMinMaxDate = salaryService.getSalaryListMinMaxDate(id);
		
		if (salaryInputMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			salaryInputMinMaxDate = new HashMap<>();
			salaryInputMinMaxDate.put("SALARY_MIN_DATE", now);
			salaryInputMinMaxDate.put("SALARY_MAX_DATE", now);
		}
		
		model.addAttribute("salaryInputMinMaxDate", salaryInputMinMaxDate);
		
		return "/salary/salaryInput";
	}
	
	// 관리자 급여입력 내역 조회 (POST)
	// 페이지 첫 로딩 시 입력된 급여 테이블 출력
	@PostMapping("/salaryInput")
	@ResponseBody
	public List<Map<String, Object>> getSalaryInput(@RequestParam Map<String, Object> map) {
		log.info("============= salaryInput POST =============");
		
		map.put("id", "admin");
		List<Map<String, Object>> salaryInputData = salaryService.getSalaryList(map);
		
		return salaryInputData;
	}
	
	// 관리자 급여입력
	@PostMapping("/writeSalary")
	public String addMember(@RequestParam Map<String, Object> param, Model model) {
		log.info("============= writeSalary =============");
		
//		<< CREATE_DATE 추가 >>
		LocalDateTime now = LocalDateTime.now();
		String CREATE_DATE = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		param.put("CREATE_DATE", CREATE_DATE);
		
		System.out.println(param.toString());
		
		int insertCount = salaryService.writeSalary(param);
		System.out.println(insertCount);
		
		return "redirect:/salaryInput";
	}	
	
    // 급여 정보 (Test)
	@GetMapping("/salaryInfo")
	public String salaryInfo(Map<String, Object> map, Model model) {
		log.info("============= salaryInfo =============");
		
		String id = "admin";
		Map<String, Object> salaryInfoMinMaxDate = salaryService.getSalaryListMinMaxDate(id);
		
		if (salaryInfoMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			salaryInfoMinMaxDate = new HashMap<>();
			salaryInfoMinMaxDate.put("SALARY_MIN_DATE", now);
			salaryInfoMinMaxDate.put("SALARY_MAX_DATE", now);
		}
		
		model.addAttribute("salaryInfoMinMaxDate", salaryInfoMinMaxDate);
		
		return "/salary/salaryInfo";
	}
	
	// 수정 버튼을 클릭시 해당 멤버의 급여 정보를 가져옴 (Test)
    @PostMapping("/getModalContent")
    @ResponseBody
    public Map<String, Object> getModalContent(@RequestParam("id") String id) {
    	log.info("============= getModalContent POST start =============");
    	
    	log.info(id);
    	
        // ID에 해당하는 데이터를 가져와서 model에 추가
    	Map<String, Object> salaryData = salaryService.findSalaryById(id); // DB에서 ID에 맞는 정보를 가져옴
    	
    	log.info("Salary Data : " + salaryData.toString());
    	
    	log.info("============= getModalContent POST end =============");
    	
    	return salaryData;
    }
	
}
