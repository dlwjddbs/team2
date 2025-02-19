package com.itwillbs.util;


import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.itwillbs.service.ManufactureService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DynamicMethodInvoker {
	
	private final ManufactureService manufactureService;
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public <T> Map<String, Object> invokeServiceMethod(String prefix, String urlid, Class<T> paramType, T paramValue) {
		try {
			String methodName = prefix + capitalize(urlid);
			Method method = manufactureService.getClass().getMethod(methodName, paramType);

			return (Map<String, Object>) method.invoke(manufactureService, paramValue);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("메서드 없음: " + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("메서드의 파라미터 타입이 일치하지 않음: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new RuntimeException("메서드 호출 중 오류 발생: " + e.getMessage(), e);
		}
	}

}
