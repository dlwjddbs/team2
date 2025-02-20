package com.itwillbs.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SelectMapperExecutor {
	
	private final ApplicationContext applicationContext;
	
	public Map<String, Object> execute(Map<String, Object> requestData, String mapperName, String methodName) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		boolean result = true;
		String message = methodName + " 성공";
		
		try {
			// Bean의 이름의 첫글자는 자동으로 소문자로 변환되어 저장됨 주의!
			Object mapper = applicationContext.getBean(mapperName);
			
            // 매퍼 인터페이스 파라미터 반드시 Map<String, Object>로 작성 필수!!!!!
            Method method = mapper.getClass().getMethod(methodName, Map.class);
            
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) method.invoke(mapper, requestData);
            
			content.put("contents", dataList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = methodName + " 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
}
