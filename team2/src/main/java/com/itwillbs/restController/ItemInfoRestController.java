package com.itwillbs.restController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.EquipmentService;
import com.itwillbs.service.ItemInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class ItemInfoRestController {
	
	private final ItemInfoService itemInfoService;
	
	private final String iteminfo_url = "/iteminfo/item";
	
	@GetMapping(iteminfo_url)
	public Map<String, Object> getItemInfo() {
		return itemInfoService.selectItemInfo();
	}	
}