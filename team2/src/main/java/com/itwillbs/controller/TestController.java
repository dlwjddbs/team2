package com.itwillbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Member;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.TestService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class TestController {
	
	private final TestService testService;
	private final MemberService memberService;
	
	// MyBatis흐름
	// Controller -> Service -> Mapper.java -> Mapper.xml
	@GetMapping("/testMyBatis")
	public String testMyBatis() {
		System.out.println(testService.getTest());
		return "index";
	}
	
	@GetMapping("/advanced")
	public String advanced() {
		return "/attendance/advanced";
	}
	
	@GetMapping("/data")
	public String data() {
		return "data";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

//	Security 적용 전 임시 로그인 로직
	@PostMapping("/login")
	public String loginPost(MemberDTO memberDTO, HttpSession session) {

		log.info("login");
		log.info(memberDTO.toString());

		Member member = memberService.findByIdAndPasswd(memberDTO);

		if (member != null) {
			session.setAttribute("id", member.getId());

			log.info("로그인 성공: " + member.getId());
			
			return "redirect:/mypage"; 

		} else {
			log.info("로그인 실패: 아이디 또는 비밀번호 불일치");
			return "redirect:/login"; 
		}

	}

	
	@GetMapping("/modals")
	public String modals() {
		return "modals";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
}


