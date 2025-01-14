package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.itwillbs.service.MenuService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@ControllerAdvice
@RequiredArgsConstructor
@Log
public class MenuAdviceController {
	
	private final MenuService menuService;
	
	@ModelAttribute
	public void sideMenuList(HttpServletRequest request, Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    // 페이지 로딩 시만 조회 -> ajax는 제외.
	    if ("GET".equalsIgnoreCase(request.getMethod())) {
	    	// UserDetails가 null일 때가 있음 예외 처리.
	    	// Cannot invoke "org.springframework.security.core.userdetails.UserDetails.getAuthorities()" because "userDetails" is null
	        if (userDetails != null) {
	        	String auth = userDetails.getAuthorities().toString();
	        	String auth2 = auth.substring(1, auth.length() - 1).split("_")[1];
	            
	        	if (!auth2.isEmpty()) {
	                List<Map<String, Object>> menus = menuService.selectMenu(auth2);
	                model.addAttribute("menus", menus);
	        	}
	        }
	    } else {
	    	log.info("MenuController - sideMenuList() UserDetails IS NULL");
	    }
	}

}
