package com.itwillbs.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
	
	// 사원별 급여조회
	@GetMapping("/salary/salaryListEmployee")
	public String salaryInput(Map<String, Object> map, Model model, HttpSession session) {

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
	@GetMapping("/salary/salaryInput")
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
	@PostMapping("/salary/salaryInput")
	@ResponseBody
	public List<Map<String, Object>> getSalaryInput(@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> salaryMember = salaryService.getSalaryMember();
		return salaryMember;
	}	
	
	// 관리자 급여입력 내역 조회
	// 페이지 첫 로딩 시 입력된 급여 테이블 출력
	@GetMapping("/salary/salaryInputList")
	public String salaryInputList(@AuthenticationPrincipal User user, Map<String, Object> map, Model model) {	
		log.info("============= salaryInputList =============");
		
        if (user == null) {
            return "redirect:/login"; 
        }

        // 로그인한 유저의 권한 가져오기
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        
		//String id = "admin";
 		String id = null;
         
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            log.info("ADMIN 권한입니다.");
            id = "admin";
        } else {
         	log.info("USER 권한입니다.");
            id = user.getUsername();
        }
        
		log.info(id);
		
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
	@PostMapping("/salary/salaryInputList")
	@ResponseBody
	public List<Map<String, Object>> getSalaryInputList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		log.info("============= salaryInputList POST =============");
		
        // 로그인한 유저의 권한 가져오기
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        
        //user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            log.info("ADMIN 권한입니다.");
     		map.put("id", "admin");
        } else {
            log.info("USER 권한입니다.");
     		map.put("id", user.getUsername());
        }
        
        List<Map<String, Object>> salaryInputData = salaryService.getSalaryList(map);
        
		return salaryInputData;
	}
	
	// 관리자 급여입력 (POST)
	@PostMapping("/salary/writeSalary")
	@ResponseBody
	public String writeSalary(@RequestParam Map<String, Object> param, Model model) {
		log.info("============= writeSalary =============");
		
		log.info(param.toString());
		
		salaryService.writeSalary(param);
		
		return "급여 입력 성공!";
	}

	// 확정버튼 클릭시 확정상태 변경
	@PostMapping("/salary/fixedSalary")
	public String fixedSalary(@RequestParam Map<String, Object> param, Model model) {
				
		log.info("============= fixedSalary =============");
		log.info(param.toString());

		salaryService.updatefixedSalary(param);
		
		return "redirect:/salary/salaryInputList";
	}
	
    // 관리자 급여 정보 (Test)
	@GetMapping("/salary/salaryInfo")
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
	@PostMapping("/salary/getSalaryInfo")
	@ResponseBody
	public List<Map<String, Object>> getSalaryInfo(@RequestParam Map<String, Object> map) {
		log.info("============= getSalaryInfo POST =============");
		
		map.put("id", "admin");
		List<Map<String, Object>> salaryInfoData = salaryService.getSalaryList(map);
		
		return salaryInfoData;
	}
	
	// 입력 버튼을 클릭시 해당 멤버의 급여 정보를 가져옴 (기본급만 조회)
	@PostMapping("/salary/salaryWriteModal")
    @ResponseBody
    public Map<String, Object> salaryWriteModal(@RequestParam("id") String id, @RequestParam(value = "payday", required = false) String payday) {
    	log.info("============= salaryWriteModal POST start =============");
    	
    	log.info(id);
    	log.info(payday);
    	
    	Map<String, Object> salaryMap = new HashMap<>();
    	salaryMap.put("id", id);
    	
    	// payday가 없으면 null로 들어가야하는데 빈 문자열로 인식하는 문제
    	// 파라미터를 명확히 null로 처리
    	salaryMap.put("payday", payday == null || payday.trim().isEmpty() ? null : payday);

        // DB에서 ID에 해당하는 데이터를 가져와서 model에 추가
    	Map<String, Object> salaryWriteData = salaryService.findSalaryWriteById(salaryMap);
    	
    	log.info("Salary Write Data : " + salaryWriteData);
    	
    	log.info("============= getModalContent POST end =============");
    	
    	return salaryWriteData;
    }
	
	// 사원번호를 클릭시 해당 멤버의 급여 정보를 가져옴 (상여금 포함 조회)
    @PostMapping("/salary/getModalContent")
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
    
	// 사원번호를 클릭시 해당 멤버의 야간수당 정보를 가져옴
    @PostMapping("/salary/getNightBonus")
    @ResponseBody
    public Map<String, Object> getNightBonus(@RequestParam("id") String id, @RequestParam(value = "payday", required = false) String payday) {
    	log.info("============= getNightBonus POST start =============");
    	
    	log.info(id);
    	log.info(payday);
    	
    	Map<String, Object> salaryMap = new HashMap<>();
    	salaryMap.put("id", id);
    	
    	// payday가 없으면 null로 들어가야하는데 빈 문자열로 인식하는 문제
    	// 파라미터를 명확히 null로 처리
    	salaryMap.put("payday", payday == null || payday.trim().isEmpty() ? null : payday);

        // DB에서 ID에 해당하는 데이터를 가져와서 model에 추가
    	// 야간 수당을 조회 (귀속연월 조건으로 추가하여 조회)
    	Map<String, Object> nightBonusData = salaryService.findNightBonusById(salaryMap);
    	
    	log.info("nightBonus Data : " + nightBonusData);
    	
    	log.info("============= getNightBonus POST end =============");
    	
    	return nightBonusData;
    }
    
    // 수정 버튼을 클릭시 해당 멤버의 급여 정보를 수정
    @PostMapping("/salary/editSalary")
    @ResponseBody
    public String updateSalary(@RequestParam Map<String, Object> map) {
    	log.info("============= updateSalary POST start =============");
    	
    	log.info(map.toString());
    	
    	salaryService.updateSalary(map);
    	
    	log.info("============= updateSalary POST end =============");
    	
    	//return "redirect:/salaryInfo";
    	return "급여 수정 성공!";
    }
    
    // 급여 입력 삭제
    @PostMapping("/salary/deleteSalary")
    @ResponseBody
    public String deleteSalary(@RequestParam Map<String, Object> map) {
    	log.info("============= deleteSalary POST start =============");
    	
    	log.info(map.toString());
    	
    	salaryService.deleteteSalary(map);
    	
    	log.info("============= deleteSalary POST end =============");
    	
    	return "급여 삭제 성공!";
    }
    
    // 관리자 급여 이체 현황 조회
 	@GetMapping("/salary/salaryTransferList")
 	public String salaryTransferList(@AuthenticationPrincipal User user, Map<String, Object> map, Model model, HttpSession session) {
 		log.info("============= salaryTransferList =============");
 		
        if (user == null) {
            return "redirect:/login"; 
        }  
         
        // 로그인한 유저의 권한 가져오기
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        
		//String id = "admin";
 		String id = null;
         
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            log.info("ADMIN 권한입니다.");
            id = "admin";
        } else {
         	log.info("USER 권한입니다.");
            id = user.getUsername();
        }
        
        log.info(id);
        
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
 	
 	// 관리자 급여 이체 현황 조회 (POST)
 	@PostMapping("/salary/salaryTransferList")
 	@ResponseBody
 	public List<Map<String, Object>> salaryTransferList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
 		log.info("============= salaryTransferList POST =============");
 		
        // 로그인한 유저의 권한 가져오기
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        
        //user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            log.info("ADMIN 권한입니다.");
     		map.put("id", "admin");
        } else {
            log.info("USER 권한입니다.");
     		map.put("id", user.getUsername());
        }
         
 		List<Map<String, Object>> salaryTransferData = salaryService.getSalaryTransferList(map);
 		
 		return salaryTransferData;
 	}
 	
}
