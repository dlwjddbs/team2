package com.itwillbs.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ControllerLoggingInterceptor implements HandlerInterceptor {

	// 테스트 로그 확인용
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            String controllerName = handlerMethod.getBeanType().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();

            System.out.println("현재 실행 중인 컨트롤러: " + controllerName);
            System.out.println("현재 실행 중인 메서드: " + methodName);
        }
        return true;
    }
	
}
