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
	
    private String getCallingMethodName() {
        return StackWalker.getInstance()
                .walk(frames -> frames.skip(2).findFirst().map(StackWalker.StackFrame::getMethodName))
                .orElse("UNKNOWN_METHOD");
    }
    
    private String getCallingServiceName() {
        return StackWalker.getInstance()
                .walk(frames -> frames.skip(2).findFirst().map(f -> f.getClassName()))
                .orElse("UNKNOWN_SERVICE");
    }
    
    private Object getMapperBean(String serviceName) {
    	// Bean의 이름의 첫글자는 자동으로 소문자로 변환되어 저장됨
        String mapperBeanName = Character.toLowerCase(serviceName.charAt(0)) + serviceName.substring(1).replace("Service", "Mapper");

        return applicationContext.getBean(mapperBeanName);
    }

	@SuppressWarnings("unused")
	public Map<String, Object> execute(Map<String, Object> requestData) {
		// 함수형 인터페이스 Function<T, R> -> T를 인자로 받고 R을 리턴한다.
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		String methodName = getCallingMethodName();
		
        String fullServiceName = getCallingServiceName();
        String serviceName = fullServiceName.substring(fullServiceName.lastIndexOf('.') + 1);
        String mapperBeanName = serviceName.replace("Service", "Mapper");
		
		boolean result = true;
		String message = methodName + " 성공";
		
		try {
			Object mapper = getMapperBean(serviceName);
			
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
