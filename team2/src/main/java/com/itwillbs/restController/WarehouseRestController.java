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

import com.itwillbs.service.WarehouseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
@RestController
public class WarehouseRestController {
	
	private final WarehouseService warehouseService;
	
	private final String warehouse_url = "/warehouse/whse";
	private final String location_url = "/warehouse/location";
	
	@GetMapping(warehouse_url)
	public Map<String, Object> getWarehouse() {
		return warehouseService.selectWarehouse();
	}	
	
	@PutMapping(warehouse_url)
	public Map<String, Object> modifyWarehouse(@RequestBody Map<String, Object> requestData) {
		return warehouseService.modifyWarehouse(requestData);
	}	
	
	@DeleteMapping(warehouse_url)
	public Map<String, Object> deleteWarehouse(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> warehouseIds = Arrays.asList(decodedIds.split(","));
        
        return warehouseService.deleteWarehouse(warehouseIds);
	}
	
	@GetMapping(location_url)
	public Map<String, Object> getLocation(@RequestParam Map<String, Object> requestData) {
		return warehouseService.selectLocation(requestData);
	}
	
	@PostMapping(location_url)
	public Map<String, Object> addLocation(@RequestBody Map<String, Object> requestData) {
	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		
        return warehouseService.addLocation(createdRows);
	}
	
	@DeleteMapping(location_url)
	public Map<String, Object> deleteLocation(@RequestHeader("X-Delete-IDs") String encodedIds) {
		// 한글ID 넘어올 시 변환
	    String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
	    List<String> locationIds = Arrays.asList(decodedIds.split(","));
	    
        return warehouseService.deleteLocation(locationIds);
	}	
	
}


