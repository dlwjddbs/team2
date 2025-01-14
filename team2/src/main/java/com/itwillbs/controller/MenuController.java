package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.itwillbs.service.MenuService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class MenuController {
	
	private final MenuService menuService;
	
	@ModelAttribute
	public void sideMenuList(HttpServletRequest request, Model model) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
        	List<Map<String, Object>> menus = menuService.selectMenu();
        	
        	model.addAttribute("menus", menus);
        }
	}
}
