package com.itwillbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequestMapping(method = RequestMethod.GET, value = "/manufacture")
@RequiredArgsConstructor
@Log
public class ManufactureController {
	
	@GetMapping("/{urlid}")
	public String workcenter(@PathVariable("urlid") String urlid) {
		return "/manufacture/" + urlid;
	}
	
}


