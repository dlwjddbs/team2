package com.itwillbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@GetMapping("/department")
	public String dept(HttpSession session) {
//		if (session.getAttribute("id") == null) {
//            return "redirect:/login"; 
//        }
		
		return "/dept/department";
	}
	
	@PostMapping("getUpperDept")
	@ResponseBody
	public List<Map<String, Object>> getUpperDept(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> upperList = deptService.getUpperDept(map);
		
		return upperList;
	}
	
	@PostMapping("addUpperDept")
	@ResponseBody
	public Map<String, Object> addUpperDept(@RequestParam Map<String, Object> map) {
		System.out.println("받은 데이터: " + map);
		Map<String, Object> message = deptService.insertUpperDept(map);
		
		return message;
	}
	
	 @GetMapping("/getCommonCodeIds")
	 @ResponseBody
	    public List<Map<String, Object>> getCommonCodeIds(@RequestParam Map<String, Object> map, Model model) {
		
		 return deptService.getCcodeList(map);
	    }
	 
	 @GetMapping("/getDepMng")
	 @ResponseBody
	    public List<Map<String, Object>> getDepMng(@RequestParam Map<String, Object> map, Model model) {
		
		 return deptService.getDepMngList(map);
	    }
	
}
