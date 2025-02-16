package com.itwillbs.restController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@PutMapping(iteminfo_url)
	public Map<String, Object> modifyItemInfo(@RequestBody Map<String, Object> requestData) {
        return itemInfoService.modifyItemInfo(requestData);
	}
	
	@DeleteMapping(iteminfo_url)
	public Map<String, Object> deleteItemInfo(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> iteminfoIds = Arrays.asList(decodedIds.split(","));
        
        return itemInfoService.deleteItemInfo(iteminfoIds);
	}	
}