package com.itwillbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
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
	public void sideMenuList(@AuthenticationPrincipal User user
							, HttpServletRequest request
							, Model model
							, @AuthenticationPrincipal UserDetails userDetails
							, @CookieValue(value = "menuCategory", defaultValue = "") String menuCategory) {
	    // 페이지 로딩 시만 조회 -> ajax는 제외.
		if (!"GET".equalsIgnoreCase(request.getMethod())) {
			log.info("MenuController - sideMenuList() UserDetails IS NULL");
			
			return;
		}
		
    	if (user != null) {
        	String id = user.getUsername();
            Map<String, Object> userInfo = menuService.selectUser(id);
            model.addAttribute("userInfo", userInfo);
        }
    	// UserDetails가 null일 때가 있음 예외 처리.
    	// Cannot invoke "org.springframework.security.core.userdetails.UserDetails.getAuthorities()" because "userDetails" is null
    	if (userDetails == null) {
    		return;
    	}
    	
    	String auth = userDetails.getAuthorities().toString();
    	String auth2 = auth.substring(1, auth.length() - 1).split("_")[1];
        
    	if (!auth2.isEmpty()) {
    		Map<String, String> map = new HashMap<>();
    		map.put("auth", auth2);
    		map.put("menuCategory", menuCategory);
            List<Map<String, Object>> menus = menuService.selectMenu(map);
            model.addAttribute("menus", menus);
            
            List<Map<String, Object>> top_menus = menuService.selectTopMenu(auth2);
            model.addAttribute("top_menus", top_menus);
    	}
	}

}
