package com.itwillbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method = RequestMethod.GET, value = "/manufacture")
public class ManufactureController {
	
	@GetMapping("/{urlid}")
	public String manufacture(@PathVariable("urlid") String urlid) {
		return "/manufacture/" + urlid;
	}
	
}
