package com.itwillbs.controller;

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

    
	@GetMapping("/commute/cmt")
	public String cmt() {
		
		return "commute/cmt";
		}

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
	
	
	@PostMapping("/cmt/checkIn")
	public String checkIn(@RequestParam Map<String, Object> map, Model model) {
	    String memberId = "20241222"; // 예시로 하드코딩된 ID, 실제로는 로그인된 사용자 ID로 대체


	    // 출근 시간 가져오기
	    Map<String, Object> checkInData = cmtService.getCheckInTime(memberId);
	    
	    model.addAttribute("checkInTime", checkInData.get("CHECK_IN_TIME"));
	    return "commute/cmt";  // cmt.html로 이동
	}
	
}
