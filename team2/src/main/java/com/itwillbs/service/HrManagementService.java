package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.HrManagementMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HrManagementService {
	private final HrManagementMapper hrManagementMapper;
	
	public List<Map<String, Object>> getmemberList() {
		System.out.println("=============Service List=============");
		return hrManagementMapper.selectmemberList();
	}
}
