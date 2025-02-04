package com.itwillbs.restController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.EquipmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class EquipmentRestController {
	
	private final EquipmentService equipmentService;
	
	private final String equipment_url = "/equipment/equip";
	
	@GetMapping(equipment_url)
	public Map<String, Object> getEquipment() {
		log.info("============= getEquipment =============");
		
		return equipmentService.selectEquipment();
	}	
	
//	@PutMapping(equipment_url)
//	public Map<String, Object> modifyEquipment(@RequestBody Map<String, Object> requestData) {
//		return equipmentService.modifyEquipment(requestData);
//	}	
//	
//	@DeleteMapping(equipment_url)
//	public Map<String, Object> deletEquipment(@RequestHeader("X-Delete-IDs") String encodedIds) {
//		// 한글ID 넘어올 시 변환
//	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
//	    List<String> equipmentIds = Arrays.asList(decodedIds.split(","));
//        
//        return equipmentService.deleteEquipment(equipmentIds);
//	}	
	
}


