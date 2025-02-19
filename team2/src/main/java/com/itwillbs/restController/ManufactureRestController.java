package com.itwillbs.restController;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.util.DynamicMethodInvoker;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/rest/manufacture")
@RestController
public class ManufactureRestController {
	
	private final DynamicMethodInvoker dynamicMethodInvoker;

	@GetMapping("/{urlid}")
	public Map<String, Object> invokeGetMethod(@PathVariable("urlid") String urlid, @RequestParam Map<String, Object> requestData) {
		return dynamicMethodInvoker.invokeServiceMethod("select", urlid, Map.class, requestData);
	}

	@PutMapping("/{urlid}")
	public Map<String, Object> invokePutMethod(@PathVariable("urlid") String urlid, @RequestBody Map<String, Object> requestData) {
		return dynamicMethodInvoker.invokeServiceMethod("modify", urlid, Map.class, requestData);
	}

	@PostMapping("/{urlid}")
	public Map<String, Object> invokePostMethod(@PathVariable("urlid") String urlid, @RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>) requestData.get("createdRows");

		return dynamicMethodInvoker.invokeServiceMethod("insert", urlid, List.class, createdRows);
	}

	@DeleteMapping("/{urlid}")
	public Map<String, Object> invokeDeleteMethod(@PathVariable("urlid") String urlid, @RequestHeader("X-Delete-IDs") String encodedIds) {
		String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);  // 한글 ID가 넘어올 경우 디코딩
		List<String> idList = Arrays.asList(decodedIds.split(","));

		return dynamicMethodInvoker.invokeServiceMethod("delete", urlid, List.class, idList);
	}
	
}
