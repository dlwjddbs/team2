package com.itwillbs.util;


import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DynamicMethodInvoker {
	
	private final ApplicationContext applicationContext;
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	private Object getServiceBean(String serviceName) {
		// 클래스가 @Service로 등록될 때 Bean의 이름의 첫글자는 자동으로 소문자로 변환되어 저장됨
		String beanName = Character.toLowerCase(serviceName.charAt(0)) + serviceName.substring(1) + "Service";
		
		return applicationContext.getBean(beanName);
	}

	public <T> Map<String, Object> invokeServiceMethod(String serviceName, String prefix, String urlid, Class<T> paramType, T paramValue) {
		try {
			Object service = getServiceBean(serviceName);
			String methodName = prefix + capitalize(urlid);
			Method method = service.getClass().getMethod(methodName, paramType);

			return (Map<String, Object>) method.invoke(service, paramValue);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("메서드 없음: " + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("메서드의 파라미터 타입이 일치하지 않음: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new RuntimeException("메서드 호출 중 오류 발생: " + e.getMessage(), e);
		}
	}

}
