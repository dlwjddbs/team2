package com.itwillbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


@Controller
@RequiredArgsConstructor
@Log
public class CmtController {
	
	
    private final CmtService cmtService;

    
//	@GetMapping("/commute/cmt")
//	public String cmt() {
//		
//		return "commute/cmt";
//		}
	
	@PostMapping("/cmt/start")
	public String cmtPro(@RequestParam Map<String, Object> map, Model model) {
		String id = "20241222";
		map.put("memberId", id);		
		cmtService.getCheckIn(map);
		
		return "redirect:/commute/cmt";
		}
	
	@PostMapping("/cmt/end")
	public String endPro(@RequestParam Map<String, Object> map, Model model) {
	    String id = "20241222"; // 사용자 ID
	    map.put("memberId", id);
	    cmtService.updateCheckOut(map);

	    return "redirect:/commute/cmt";
	}
	
	
	@GetMapping("/commute/cmt")
	public String getTodayHistory(@RequestParam Map<String, Object> map,Model model) {
		String id = "20241222";
		
		map.put("memberId", id);
		Map<String, Object> todayHistory = cmtService.getTodayHistory(map);
		
		  // 출근 시간과 퇴근 시간이 존재하는지 확인
	    boolean isCheckedIn = todayHistory != null && todayHistory.get("check_in_time") != null;
	    boolean isCheckedOut = todayHistory != null && todayHistory.get("check_out_time") != null;
		
		 // todayHistory가 null이면 확인
	    if (todayHistory == null) {
	        System.out.println("todayHistory is null");
	    } else {
	        System.out.println("todayHistory: " + todayHistory);
	    }
		
		model.addAttribute("todayHistory", todayHistory);
		model.addAttribute("isCheckedIn", isCheckedIn);  // 출근 여부
	    model.addAttribute("isCheckedOut", isCheckedOut); // 퇴근 여부
		
		return "commute/cmt";
	}
	

}
