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
	
	// 급여조회
	@GetMapping("/salaryHistory")
	public String salaryhistory(Map<String, Object> map, Model model) {
		log.info("============= salaryhistory =============");
		
		List<Map<String, Object>> salaryHistory = salaryService.getSalaryList();
		model.addAttribute("salaryHistory", salaryHistory);
		
		Map<String, Object> salaryHistoryMinMaxDate = salaryService.getSalaryHistoryMinMaxDate();
		model.addAttribute("salaryHistoryMinMaxDate", salaryHistoryMinMaxDate);
		
		return "/salary/salaryHistory";
	}
	
	@PostMapping("/getSalaryHistory")
	@ResponseBody
	public List<Map<String, Object>> getSalaryHistory(@RequestParam Map<String, Object> map) {
		log.info("============= getSalaryHistory =============");
		
		List<Map<String, Object>> allSalaryHistory = salaryService.getSalaryHistory(map); 
		
		return allSalaryHistory;
	}
	
	// 급여입력(관리자)
	@GetMapping("/salaryInput")
	public String salaryInput() {
		return "/salary/salaryInput";
	}
	
	// @PostMapping("/salaryInput")
	
	@PostMapping("/writeSalary")
	public String addMember(@RequestParam Map<String, Object> param, Model model) {
		log.info("============= writeSalary =============");
		
//		String email = param.get("email_id").toString() + "@" + param.get("email_domain").toString();
		
		LocalDateTime now = LocalDateTime.now();
		String CREATE_DATE = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		// ======================================================================================================
		
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
		log.info("============= memberSalaryList =============");
		
		List<Map<String, Object>> salaryList = salaryService.memberSalaryList();
		log.info(salaryList.toString());
		
		return salaryList;
	}
	
	// 수정 버튼을 클릭시 해당 멤버의 정보를 가져옴 (Test)
	// 급여 정보를 가져오는걸로 수정 예정
    @PostMapping("/getModalContent")
    public String getModalContent(@RequestParam("id") String id, Model model) {
    	log.info("============= getModalContent POST start =============");
    	
    	log.info(id);
    	
        // ID에 해당하는 데이터를 가져와서 model에 추가
//    	Map<String, Object> salaryData = salaryService.findSalaryById(id); // DB에서 ID에 맞는 정보를 가져옴
//    	
//    	log.info("Salary Data : " + salaryData.toString());
//    	
//    	model.addAttribute("salaryData", salaryData);
    	
    	log.info("============= getModalContent POST end =============");
    	
    	// "includes/modals/edit" 반환
        return "includes/modals/edit :: edit";         
    }
    
    // 급여 정보 (Test)
	@GetMapping("/salaryInfo")
	public String salaryInfo(Map<String, Object> map, Model model) {
		log.info("============= salaryInfo =============");
		
		return "/salary/salaryInfo";
	}
	
	
}
