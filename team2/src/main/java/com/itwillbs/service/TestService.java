package com.itwillbs.service;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.TestMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	private final TestMapper testMapper;
	
	public String getTest() {
		return testMapper.selectTest();
	}
}
