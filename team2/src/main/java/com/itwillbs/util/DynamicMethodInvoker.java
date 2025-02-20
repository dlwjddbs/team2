package com.itwillbs.util;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DynamicMethodInvoker {
	
	private final ApplicationContext applicationContext;
	private final SelectMapperExecutor selectMapperExecutor;
	
	private static final String BEAN_TYPE_SERVICE = "Service";
	private static final String BEAN_TYPE_MAPPER = "Mapper";
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	private String getBeanName(String serviceName, String type) {
		// 클래스가 @Service로 등록될 때 Bean의 이름의 첫글자는 자동으로 소문자로 변환되어 저장됨
		return Character.toLowerCase(serviceName.charAt(0)) + serviceName.substring(1) + type;
	}
	
	private Object getBean(String serviceName, String type) {
		String beanName = getBeanName(serviceName, type);
		
		return applicationContext.getBean(beanName);
	}
	
	private boolean methodExists(Object bean, String methodName, Class<?> paramType) {
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (method.getName().equals(methodName) 
					&& method.getParameterCount() == 1 
					&& method.getParameterTypes()[0].equals(paramType)) {
				return true;
			}
		}
		return false;
	}

	public <T> Map<String, Object> invokeServiceMethod(String serviceName, String prefix, String urlid, Class<T> paramType, T paramValue) {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			String methodName = prefix + capitalize(urlid);
			
			// 서비스에 메서드 존재하는지 조회
			Object service = getBean(serviceName, BEAN_TYPE_SERVICE);
			if (methodExists(service, methodName, paramType)) {
				Method method = service.getClass().getMethod(methodName, paramType);
				
				return (Map<String, Object>) method.invoke(service, paramValue);
			}

			// 매퍼에 메서드 존재하는지 조회
			// SELECT는 서비스에 메서드 생성 안하면 기본 틀로 자동 매핑됨. 서비스없이 mapper.java 부터 작성하면됨.
			// SELECT일때 서비스에 생성한 메서드 있으면 위의 조건에 걸려서 작성된 코드가 우선으로 실행됨.
			Object mapper = getBean(serviceName, BEAN_TYPE_MAPPER);
			if ("select".equals(prefix) && methodExists(mapper, methodName, paramType)) {
				String mapperName = getBeanName(serviceName, BEAN_TYPE_MAPPER);
				
				return selectMapperExecutor.execute((Map<String, Object>) paramValue, mapperName, methodName);
			}
			
			resultMap.put("result", false);
			resultMap.put("message", methodName + "메서드 없음");
			
			return resultMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
