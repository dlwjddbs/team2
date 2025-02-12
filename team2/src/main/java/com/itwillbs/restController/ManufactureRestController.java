package com.itwillbs.restController;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class ManufactureRestController {
	
	private final ManufactureService manufactureService;
	
	private final String workcenter_url = "/manufacture/workcenter";
	private final String manager_url = "/manufacture/workcenterManager";
	private final String equipment_url = "/manufacture/equipment";
	private final String process_url = "/manufacture/process";
	private final String routing_url = "/manufacture/routing";
	private final String item_url = "/manufacture/routingItem";
	private final String routing_sequence_url = "/manufacture/sequence";
	
	@GetMapping(workcenter_url)
	public Map<String, Object> getWorkcenter() {
		return manufactureService.selectWorkcenter();
	}	
	
	@PutMapping(workcenter_url)
	public Map<String, Object> modifyWorkcenter(@RequestBody Map<String, Object> requestData) {
		return manufactureService.modifyWorkcenter(requestData);
	}	
	
	@DeleteMapping(workcenter_url)
	public Map<String, Object> deletWorkcenter(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> workcenterIds = Arrays.asList(decodedIds.split(","));
        
        return manufactureService.deleteWorkcenter(workcenterIds);
	}
	
	@GetMapping(manager_url)
	public Map<String, Object> getMember(@RequestParam Map<String, Object> requestData) {
		return manufactureService.selectMember(requestData);
	}	
	
	@GetMapping(equipment_url)
	public Map<String, Object> getEquipment(@RequestParam Map<String, Object> requestData) {
		return manufactureService.selectEquipment(requestData);
	}
	
	@PostMapping(equipment_url)
	public Map<String, Object> addEquipment(@RequestBody Map<String, Object> requestData) {
	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		
        return manufactureService.addEquipment(createdRows);
	}
	
	@DeleteMapping(equipment_url)
	public Map<String, Object> deletEquipment(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> equipmentIds = Arrays.asList(decodedIds.split(","));
	    
        return manufactureService.deleteEquipment(equipmentIds);
	}
	
	@GetMapping(process_url)
	public Map<String, Object> getProcess(@RequestParam Map<String, Object> requestData) {
		return manufactureService.selectProcess(requestData);
	}
	
	@PutMapping(process_url)
	public Map<String, Object> modifyProcess(@RequestBody Map<String, Object> requestData) {
		return manufactureService.modifyProcess(requestData);
	}
	
	@DeleteMapping(process_url)
	public Map<String, Object> deleteProcess(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> processIds = Arrays.asList(decodedIds.split(","));
	    
        return manufactureService.deleteProcess(processIds);
	}
	
	@GetMapping(routing_url)
	public Map<String, Object> getRouting(@RequestParam Map<String, Object> requestData) {
		return manufactureService.selectRouting(requestData);
	}
	
	@GetMapping(item_url)
	public Map<String, Object> getItem(@RequestParam Map<String, Object> requestData) {
		return manufactureService.selectItem(requestData);
	}
	
	@PutMapping(routing_url)
	public Map<String, Object> modifyRouting(@RequestBody Map<String, Object> requestData) {
		return manufactureService.modifyRouting(requestData);
	}
	
	@DeleteMapping(routing_url)
	public Map<String, Object> deleteRouting(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> processIds = Arrays.asList(decodedIds.split(","));
	    
        return manufactureService.deleteRouting(processIds);
	}
	
	@GetMapping(routing_sequence_url)
	public Map<String, Object> getRoutingSequence(@RequestParam Map<String, Object> requestData) {
		return manufactureService.selectRoutingSequence(requestData);
	}
	
	@PostMapping(routing_sequence_url)
	public Map<String, Object> insertRoutingSequence(@RequestBody Map<String, Object> requestData) {
	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		
        return manufactureService.insertRoutingSequence(createdRows);
	}
	
}


