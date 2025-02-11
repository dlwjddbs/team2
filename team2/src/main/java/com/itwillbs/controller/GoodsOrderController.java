package com.itwillbs.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.PurchaseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
@Controller
@RequiredArgsConstructor
@Log
public class GoodsOrderController {
	
	@GetMapping("/goodsOrder")
	public String purchase(@AuthenticationPrincipal User user) {
//		if (session.getAttribute("id") == null) {
//      return "redirect:/login"; 
//  }
		return "goodsOrder/goodsOrder";
	}
	
}
