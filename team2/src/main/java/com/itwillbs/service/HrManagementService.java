package com.itwillbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.HrManagementMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HrManagementService {
	private final HrManagementMapper hrManagementMapper;
	
	public int addMember(Map<String, Object> param) {
		System.out.println("service param : " + param);
		return hrManagementMapper.insertMember(param);
	}
	
	public List<Map<String, Object>> getMemberList() {
		return hrManagementMapper.selectMemberList();
	}

	public List<Map<String, Object>> getOrganizationData() {
		
		List<Map<String, Object>> data = hrManagementMapper.selectOrganizationData(); 
		
		
		return data;
	}

	public void addHistory(Map<String, Object> param, String string) {
		hrManagementMapper.insertHistory(param, string);
	}


}
