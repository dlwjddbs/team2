package com.itwillbs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;

@RestController
public class SmsController {
	
	private final DefaultMessageService messageService;
	
	public SmsController(@Value("${apiKey}") String apiKey, 
            @Value("${apiSecretKey}") String apiSecretKey) {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
	}
	
}
