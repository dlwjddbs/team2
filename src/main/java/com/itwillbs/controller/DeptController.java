package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.DeptService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class DeptController {
	
	private final DeptService deptService;
	
	@GetMapping("/admin/department")
	public String dept(@AuthenticationPrincipal User user) {
//		if (session.getAttribute("id") == null) {
//            return "redirect:/login"; 
//        }
		
		return "/dept/department";
	}
	
	// 상위부서 리스트
	@PostMapping("getUpperDept")
	@ResponseBody
	public List<Map<String, Object>> getUpperDept(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> upperList = deptService.getUpperDept(map);
		
		return upperList;
	}
	
	// 상위부서 등록
	@PostMapping("addUpperDept")
	@ResponseBody
	public Map<String, Object> addUpperDept(@RequestParam Map<String, Object> map) {
		System.out.println("받은 데이터: " + map);
		Map<String, Object> message = deptService.insertUpperDept(map);
		
		return message;
	}
	
	// 공통코드 조회
	 @GetMapping("/getCommonCodeIds")
	 @ResponseBody
	    public List<Map<String, Object>> getCommonCodeIds(@RequestParam Map<String, Object> map, Model model) {
		
		 return deptService.getCcodeList(map);
	    }
	 
	 // 부서장 조회
	 @GetMapping("/getDepMng")
	 @ResponseBody
	    public List<Map<String, Object>> getDepMng(@RequestParam Map<String, Object> map, Model model) {
		
		 return deptService.getDepMngList(map);
	    }
	
	 // 상위부서 삭제
	 @PostMapping("/deleteUpperDept")
	@ResponseBody
	public Map<String, Object> deleteUpperDept(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = deptService.deleteUpperDept(map);
			
		return message;
		}
	 
	 // 상위부서 수정
	 @PostMapping("/updateUpperDept")
	 @ResponseBody
	public Map<String, Object> updateUpperDept(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = deptService.updateUpperDept(map);
			
			return message;
		}
	 
	// 하위부서 리스트
	@PostMapping("getLowerDept")
	@ResponseBody
	public List<Map<String, Object>> getLowerDept(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> lowerList = deptService.getLowerDept(map);
			
		return lowerList;
		}
	 
	//하위부서 등록
	@PostMapping("/addLowerDept")
	@ResponseBody
	public Map<String, Object> addLowerDept(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = deptService.insertLowerDept(map);
		
		return message;
	}
	
	// 하위부서 삭제
	@PostMapping("/deleteLowerDept")
	@ResponseBody
	public Map<String, Object> deleteLowerDept(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = deptService.deleteLowerDept(map);
		
		return message;
	} 
	
	// 하위부서 수정
	@PostMapping("/updateLowerDept")
	@ResponseBody
	public Map<String, Object> updateLowerDept(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = deptService.updateLowerDept(map);
				
	return message;
	}
	
	// 부서 목록
	@GetMapping("/departmentList")
	public String deptList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		List<Map<String, Object>> deptList = deptService.getDepartmentList(map);
		// noticeList가 null이면 확인
	    if (deptList == null) {
	        System.out.println("deptList is null");
	    } else {
	    }
		return "/dept/departmentList";
	} 
	
	
	// 부서 목록
		@PostMapping("getDepartmentList")
		@ResponseBody
		public List<Map<String, Object>> getDepartmentList(@RequestParam Map<String, Object> map) {
			List<Map<String, Object>> departmentList = deptService.getDepartmentList(map);
			
			return departmentList;
		}
}
