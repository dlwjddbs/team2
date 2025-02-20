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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/rest/{serviceName}/{urlid}")
@RestController
public class ManufactureRestController {
	
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
		return dynamicMethodInvoker.invokeServiceMethod(serviceName, "select", urlid, Map.class, requestData);
	}

	@PutMapping
	public Map<String, Object> invokePutMethod(@RequestBody Map<String, Object> requestData) {
		return dynamicMethodInvoker.invokeServiceMethod(serviceName, "modify", urlid, Map.class, requestData);
	}

	@PostMapping
	public Map<String, Object> invokePostMethod(@RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>) requestData.get("createdRows");

		return dynamicMethodInvoker.invokeServiceMethod(serviceName, "insert", urlid, List.class, createdRows);
	}

	@DeleteMapping
	public Map<String, Object> invokeDeleteMethod(@RequestHeader("X-Delete-IDs") String encodedIds) {
		String decodedIds = URLDecoder.decode(encodedIds, StandardCharsets.UTF_8);  // 한글 ID가 넘어올 경우 디코딩
		List<String> idList = Arrays.asList(decodedIds.split(","));

		return dynamicMethodInvoker.invokeServiceMethod(serviceName, "delete", urlid, List.class, idList);
	}
	
}
