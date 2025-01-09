package com.itwillbs.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping("/salarySendTest")
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
	
	@PostMapping("/sendEmail")
	@ResponseBody
	public String sendEmail(@RequestParam("to") String to, 
							@RequestParam("subject") String subject) throws Exception{
		// 매개변수 @RequestParam String text는 고민중
		log.info("============= sendEmail =============");
		
		StringBuffer sb = new StringBuffer();
		
		// 메일 테스트용 임시 데이터
		sb.append("<html><head></head><body>");
		sb.append("<h1> ").append("Test").append("님 의").append(" 급여명세서 입니다. </h1> ");
		sb.append("</body></html>");
		
		emailService.sendSimpleMessage(to, subject, sb.toString());
		
		return "이메일 전송 성공!";
	}

}
