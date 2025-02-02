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
	public List<Map<String, Object>> getMenuList(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> menuList = menuService.getMenuList(map);
		
		return menuList;
	}
	
	@PostMapping("/system/addMenu")
	@ResponseBody
	public Map<String, Object> addMenu(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = menuService.addMenu(map);
		
		return message;
	}
	
	@PostMapping("/system/updateMenu")
	@ResponseBody
	public Map<String, Object> updateMenu(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = menuService.updateMenu(map);
		
		return message;
	}
	
	@PostMapping("/system/deleteMenu")
	@ResponseBody
	public Map<String, Object> deleteMenu(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = menuService.deleteMenu(map);
		
		return message;
	}
	
	@PostMapping("/system/getMenuCategoryList")
	@ResponseBody
	public List<Map<String, Object>> getMenuCategoryList(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> menuCategoryList = menuService.selectMenuCategoryList(map);
		
		return menuCategoryList;
	}
	
	@PostMapping("/system/addMenuCategory")
	@ResponseBody
	public Map<String, Object> addMenuCategory(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = menuService.addMenuCategory(map);
		
		return message;
	}
	
	@PostMapping("/system/updateMenuCategory")
	@ResponseBody
	public Map<String, Object> updateMenuCategory(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = menuService.updateMenuCategory(map);
		
		return message;
	}
	
	@PostMapping("/system/deleteMenuCategory")
	@ResponseBody
	public Map<String, Object> deleteMenuCategory(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = menuService.deleteMenuCategory(map);
		
		return message;
	}
}


