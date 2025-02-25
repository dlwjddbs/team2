package com.itwillbs.restController;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.util.DynamicMethodInvoker;
import com.itwillbs.util.MethodType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/rest/{serviceName}/{urlid}")
@RestController
public class DynamicRestController {
	
	private final DynamicMethodInvoker dynamicMethodInvoker;
	
	private String serviceName;
	private String urlid;
	
	@ModelAttribute
	public void setPathVariables(@PathVariable("serviceName") String serviceName, @PathVariable("urlid") String urlid) {
		this.serviceName = serviceName;
		this.urlid = urlid;
	}

	@GetMapping
	public Map<String, Object> invokeGetMethod(@RequestParam Map<String, Object> requestData) {
		return invokeService(MethodType.SELECT, requestData, Map.class);
	}

	@PutMapping
	public Map<String, Object> invokePutMethod(@RequestBody Map<String, Object> requestData) {
		return invokeService(MethodType.MODIFY, requestData, Map.class);
	}

	@PostMapping
	public Map<String, Object> invokePostMethod(@RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>) requestData.get("createdRows");

		return invokeService(MethodType.INSERT, createdRows, List.class);
	}

	@DeleteMapping
	public Map<String, Object> invokeDeleteMethod(@RequestHeader("X-Delete-IDs") String encodedIds) {
		List<String> idList = decodeIds(encodedIds);
		
		return invokeService(MethodType.DELETE, idList, List.class);
	}
	
	private <T> Map<String, Object> invokeService(MethodType prefix, T requestData, Class<T> paramType) {
		return dynamicMethodInvoker.invokeServiceMethod(serviceName, prefix, urlid, paramType, requestData);
	}
	
	private List<String> decodeIds(String encodedIds) {
		String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);
		
		return Arrays.asList(decodedIds.split(","));
	}
	
}
