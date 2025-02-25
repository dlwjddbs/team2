package com.itwillbs.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.service.CmtService;
import com.itwillbs.service.HrManagementService;
import com.itwillbs.service.TestService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import oracle.sql.TIMESTAMP;


@Controller
@RequiredArgsConstructor
@Log
public class CmtController {
	
	
    private final CmtService cmtService;
    
	//출근 
	@PostMapping("/cmt/start")
	public String cmtPro(@AuthenticationPrincipal User user,@RequestParam Map<String, Object> map, Model model) {
		String id = user.getUsername();
		map.put("memberId", id);		
		cmtService.getCheckIn(map);
		
		return "redirect:/commute/cmt";
		}
	
	//퇴근
	@PostMapping("/cmt/end")
	public String endPro(@AuthenticationPrincipal User user,@RequestParam Map<String, Object> map, Model model) {
		String id = user.getUsername();
		
	    map.put("memberId", id);
	    cmtService.updateCheckOut(map);

	    return "redirect:/commute/cmt";
	}
	
	// 출퇴근 기록
	@GetMapping("/commute/cmt")
	public String getTodayHistory(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map,Model model) {
		 if (user.getUsername() == null) {
	            return "redirect:/login"; 
	        }
	        
		 String id = user.getUsername();
		
		map.put("memberId", id);
		Map<String, Object> todayHistory = cmtService.getTodayHistory(map);		
	    
		 // todayHistory가 null이면 확인
	    if (todayHistory == null) {
	        System.out.println("todayHistory is null");
	    } else {
	        System.out.println("todayHistory: " + todayHistory);
	    }
		
		model.addAttribute("todayHistory", todayHistory);
		
		return "commute/cmt";
	}
	

}
