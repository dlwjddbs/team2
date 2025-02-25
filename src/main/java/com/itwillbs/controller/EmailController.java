package com.itwillbs.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.EmailService;
import com.itwillbs.service.SalaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
//@RestController
@RequiredArgsConstructor
@Log
public class EmailController {
	
	private final EmailService emailService;
	private final SalaryService salaryService;
	
	// 급여명세서 전송 test용 페이지
	// 전송 기능 구현 완료시 삭제 예정
	@GetMapping("/salary/salarySendTest")
	public String salaryEmailTest(Map<String, Object> map, Model model) {
		log.info("============= salaryEmailTest =============");
		
		String id = "admin";
		Map<String, Object> salaryInfoMinMaxDate = salaryService.getSalaryListMinMaxDate(id);
		
		if (salaryInfoMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			salaryInfoMinMaxDate = new HashMap<>();
			salaryInfoMinMaxDate.put("SALARY_MIN_DATE", now);
			salaryInfoMinMaxDate.put("SALARY_MAX_DATE", now);
		}
		
		model.addAttribute("salaryInfoMinMaxDate", salaryInfoMinMaxDate);
		
		return "/salary/salarySendTest";
	}
	
	@PostMapping("/salary/sendEmail")
	@ResponseBody
	public String sendEmail(@RequestBody List<Map<String, Object>> selectedData) throws Exception{
		log.info("============= sendEmail =============");
		
		log.info(selectedData.toString());

		// 메일 내용 생성 및 전송
	    for (Map<String, Object> rowData : selectedData) {
	        try {
		        String email = (String) rowData.get("EMAIL"); // 이메일 주소
		        String payday = (String) rowData.get("PAYDAY"); // 귀속연월
	
		        // 이메일 제목과 내용 생성
		        String subject = String.format("%s월 급여명세서", payday);
		        String message = generateEmailContent(rowData);
		        
	            // 이메일 전송 서비스 호출
	            emailService.sendMail(email, subject, message);
	            
	        } catch (Exception e) {
	        	log.info("급여 명세서 메일 전송 실패!");
	        }
	    }

		return "이메일 전송 성공!";
	}
	
	// 메일 내용 생성
	private String generateEmailContent(Map<String, Object> rowData) {
	    StringBuilder sb = new StringBuilder();

	    sb.append("<html><head></head><body>");
	    sb.append("<h1>")
	      .append(rowData.get("NAME"))
	      .append("님 의 ")
	      .append(rowData.get("PAYDAY"))
	      .append("월 급여명세서입니다.</h1>");
	    sb.append("<p>귀속연월 : ").append(rowData.get("PAYDAY")).append("월</p>");
	    sb.append("<p>기본금 : ").append(rowData.get("SALARY")).append("원</p>");
	    sb.append("<p>직책수당 : ").append(rowData.get("POSITION_BONUS")).append("원</p>");
	    sb.append("<p>연휴수당 : ").append(rowData.get("HOLIDAY_BONUS")).append("원</p>");
	    sb.append("<p>야근수당 : ").append(rowData.get("NIGHT_BONUS")).append("원</p>");
	    sb.append("<p>연말수당 : ").append(rowData.get("YEAREND_BONUS")).append("원</p>");
	    sb.append("<p>만기근속포상금 : ").append(rowData.get("FULLSERVICE_BONUS")).append("원</p>");
	    sb.append("<p>성과금 : ").append(rowData.get("PERFORMANCE_BONUS")).append("원</p>");
	    sb.append("<p>실지급액 : ").append(rowData.get("TOTAL")).append("원</p>");
	    sb.append("<p>예금주명 : ").append(rowData.get("ACCOUNT_HOLDER")).append("</p>");
	    sb.append("</body></html>");

	    return sb.toString();
	}

}
