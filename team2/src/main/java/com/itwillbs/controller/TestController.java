package com.itwillbs.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.MemberService;
import com.itwillbs.service.TestService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
	public String login(HttpServletRequest request, Model model) {
        String rememberedUsername = null;
        
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("rememberedUsername".equals(cookie.getName())) {
                    rememberedUsername = cookie.getValue();
                    break;
                }
            }
        }
        
        model.addAttribute("rememberedUsername", rememberedUsername);
		
		return "login";
	}

//	Security 적용 전 임시 로그인 로직
	/*
	 * @PostMapping("/login") public String loginPost(MemberDTO memberDTO,
	 * HttpSession session) {
	 * 
	 * log.info("login"); log.info(memberDTO.toString());
	 * 
	 * Member member = memberService.findByIdAndPasswd(memberDTO);
	 * 
	 * if (member != null) { session.setAttribute("id", member.getId());
	 * session.setAttribute("authority", member.getAuthority());
	 * 
	 * log.info("로그인 성공: " + member.getId());
	 * 
	 * return "redirect:/mypage";
	 * 
	 * } else { log.info("로그인 실패: 아이디 또는 비밀번호 불일치"); return "redirect:/login"; }
	 * 
	 * }
	 */


	
	@GetMapping("/modals")
	public String modals() {
		return "modals";
	}

//	@GetMapping("/index")
//	public String index() {
//		return "index";
//	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
}


