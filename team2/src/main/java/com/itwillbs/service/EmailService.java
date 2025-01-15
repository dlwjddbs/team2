package com.itwillbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class EmailService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	// 메일 전송하기 동작
	// javax.mail -> jakarta.mail로 수정
	// @Async : 비동기 방식으로 처리
	@Async
	public void sendMail(String to, String subject, String text){
		log.info("============= sendMail 시작 =============");
		
		MimeMessage message = emailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text,true);
			
			emailSender.send(message);
			
			log.info("============= sendMail 완료 =============");
		
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}
}
