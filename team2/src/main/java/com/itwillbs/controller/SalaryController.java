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

import jakarta.servlet.http.HttpSession;
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
	@GetMapping("/salaryListEmployee")
	public String salaryInput(Map<String, Object> map, Model model, HttpSession session) {
//	public String salaryListEmployee() {
		// 관리자 페이지라 관리자 로그인 필수
//		if (session.getAttribute("id") == null) {
//            return "redirect:/login"; 
//        }
		String id = session.getAttribute("id").toString();
		Map<String, Object> salaryInputMinMaxDate = salaryService.getSalaryListMinMaxDate(id);
		
		if (salaryInputMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			salaryInputMinMaxDate = new HashMap<>();
			salaryInputMinMaxDate.put("SALARY_MIN_DATE", now);
			salaryInputMinMaxDate.put("SALARY_MAX_DATE", now);
		}
		
		model.addAttribute("salaryInputMinMaxDate", salaryInputMinMaxDate);
		
		return "/salary/salaryListEmployee";
	}
	
	// 관리자 급여입력 
	// 페이지 첫 로딩 시 사원 정보 조회
	@GetMapping("/salaryInput")
//	public String salaryInput(Map<String, Object> map, Model model, HttpSession session) {
	public String salaryInput() {
		
		// 관리자 페이지라 관리자 로그인 필수
//		if (session.getAttribute("id") == null) {
//            return "redirect:/login"; 
//        }
//		
//		log.info("============= salaryInput =============");
//		
//		String id = "admin";
//		Map<String, Object> salaryInputMinMaxDate = salaryService.getSalaryListMinMaxDate(id);
//		
//		if (salaryInputMinMaxDate == null) {
//			LocalDate now = LocalDate.now();
//			
//			salaryInputMinMaxDate = new HashMap<>();
//			salaryInputMinMaxDate.put("SALARY_MIN_DATE", now);
//			salaryInputMinMaxDate.put("SALARY_MAX_DATE", now);
//		}
//		
//		model.addAttribute("salaryInputMinMaxDate", salaryInputMinMaxDate);
		
		return "/salary/salaryInput";
	}
	
	// 관리자 급여입력 (POST)
	// 페이지 첫 로딩 시 사원 정보 조회
	@PostMapping("/salaryInput")
	@ResponseBody
	public List<Map<String, Object>> getSalaryInput(@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> salaryMember = salaryService.getSalaryMember();
		return salaryMember;
	}	
	
	
	// 관리자 급여입력 내역 조회
	// 페이지 첫 로딩 시 입력된 급여 테이블 출력
	@GetMapping("/salaryInputList")
	public String salaryInputList(Map<String, Object> map, Model model, HttpSession session) {
		
		// 관리자 페이지라 관리자 로그인 필수
//		if (session.getAttribute("id") == null) {
//            return "redirect:/login"; 
//        }
		
		log.info("============= salaryInputList =============");
		
		String id = "admin";
		Map<String, Object> salaryInputMinMaxDate = salaryService.getSalaryListMinMaxDate(id);
		
		if (salaryInputMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			salaryInputMinMaxDate = new HashMap<>();
			salaryInputMinMaxDate.put("SALARY_MIN_DATE", now);
			salaryInputMinMaxDate.put("SALARY_MAX_DATE", now);
		}
		
		model.addAttribute("salaryInputMinMaxDate", salaryInputMinMaxDate);
		
		return "/salary/salaryInputList";
	}
	
	// 관리자 급여입력 내역 조회 (POST)
	// 페이지 첫 로딩 시 입력된 급여 테이블 출력
	@PostMapping("/salaryInputList")
	@ResponseBody
	public List<Map<String, Object>> getSalaryInputList(@RequestParam Map<String, Object> map) {
		
		log.info("============= salaryInputList POST =============");
		
		map.put("id", "admin");
		List<Map<String, Object>> salaryInputData = salaryService.getSalaryList(map);
		
		return salaryInputData;
	}
	
	// 관리자 급여입력
	@PostMapping("/writeSalary")
	@ResponseBody
	public String writeSalary(@RequestParam Map<String, Object> param, Model model) {
		log.info("============= writeSalary =============");
		
		log.info(param.toString());
		
		salaryService.writeSalary(param);
//		System.out.println(insertCount);
//		
//		if(insertCount > 0) {
//			System.out.println("========insert 성공");
//			System.out.println("insertCount: " + insertCount);
//		
//		}
		
		return "급여 입력 성공!";
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
	
	// 관리자 급여 정보 조회 (POST) (Test)
	// 페이지 첫 로딩 시 입력된 급여 테이블 출력 
	@PostMapping("/getSalaryInfo")
	@ResponseBody
	public List<Map<String, Object>> getSalaryInfo(@RequestParam Map<String, Object> map) {
		log.info("============= getSalaryInfo POST =============");
		
		map.put("id", "admin");
		List<Map<String, Object>> salaryInfoData = salaryService.getSalaryList(map);
		
		return salaryInfoData;
	}
	
	// 입력, 수정 버튼을 클릭시 해당 멤버의 급여 정보를 가져옴
    @PostMapping("/getModalContent")
    @ResponseBody
    public Map<String, Object> getModalContent(@RequestParam("id") String id, @RequestParam(value = "payday", required = false) String payday) {
    	log.info("============= getModalContent POST start =============");
    	
    	log.info(id);
    	log.info(payday);
    	
    	Map<String, Object> salaryMap = new HashMap<>();
    	salaryMap.put("id", id);
    	
    	// payday가 없으면 null로 들어가야하는데 빈 문자열로 인식하는 문제
    	// 파라미터를 명확히 null로 처리
    	salaryMap.put("payday", payday == null || payday.trim().isEmpty() ? null : payday);

        // DB에서 ID에 해당하는 데이터를 가져와서 model에 추가
    	//Map<String, Object> salaryData = salaryService.findSalaryById(id);
    	Map<String, Object> salaryData = salaryService.findSalaryById(salaryMap);
    	
    	log.info("Salary Data : " + salaryData);
    	
    	log.info("============= getModalContent POST end =============");
    	
    	return salaryData;
    }
    
    // 수정 버튼을 클릭시 해당 멤버의 급여 정보를 수정
    @PostMapping("/editSalary")
    @ResponseBody
    public String updateSalary(@RequestParam Map<String, Object> map) {
    	log.info("============= updateSalary POST start =============");
    	
        //log.info("id" +  id);
        
    	log.info(map.toString());
    	
    	salaryService.updateSalary(map);
    	
    	log.info("============= updateSalary POST end =============");
    	
    	//return "redirect:/salaryInfo";
    	return "급여 수정 성공!";
    }
    
	// 관리자 급여 이체 현황 조회
	@GetMapping("/salaryTransferList")
	public String salaryTransferList(Map<String, Object> map, Model model, HttpSession session) {	
		// 관리자 페이지라 관리자 로그인 필수
//		if (session.getAttribute("id") == null) {
//            return "redirect:/login"; 
//        }
		
		log.info("============= salaryTransferList =============");
		
		String id = "admin";
		Map<String, Object> salaryTransferMinMaxDate = salaryService.getSalaryListMinMaxDate(id);
		
		if (salaryTransferMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			salaryTransferMinMaxDate = new HashMap<>();
			salaryTransferMinMaxDate.put("SALARY_MIN_DATE", now);
			salaryTransferMinMaxDate.put("SALARY_MAX_DATE", now);
		}
		
		model.addAttribute("salaryTransferMinMaxDate", salaryTransferMinMaxDate);
		
		return "/salary/salaryTransferList";
	}
	
	
}
