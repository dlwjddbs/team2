package com.itwillbs.controller;


import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.MenuService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class MenuController {
	
	private final MenuService menuService;
	
	@GetMapping("/admin/menu")
	public String menu(@AuthenticationPrincipal User user, HttpSession session) {
        if (user == null) {
            return "redirect:/login"; 
        }
		
		return "/menu/menu";
	}
	
	@PostMapping("/system/getMenuList")
	@ResponseBody
	public List<Map<String, Object>> getMenuList(@AuthenticationPrincipal User user, @RequestParam Map<String, Object> map) {
		List<Map<String, Object>> menuList = menuService.getMenuList(map);
		
		return menuList;
	}
	
}


