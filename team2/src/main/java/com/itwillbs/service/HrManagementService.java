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
		return hrManagementMapper.insertMember(param);
	}
	
	public List<Map<String, Object>> getMemberList() {
		return hrManagementMapper.selectMemberList();
	}

	public Map<String, Object> getOrganizationData() {
		Map<String, Object> OrganizationData = new HashMap<>();
		
		List<Map<String, Object>> department = hrManagementMapper.selectDepartment(); 
		
		List<Map<String, Object>> grade = hrManagementMapper.seletTable("GRADE", "NAME", "ID");
		
		List<Map<String, Object>> bank = hrManagementMapper.seletTable("BANK_CODES", "BANK_NAME", "CODE");
		
		OrganizationData.put("departments", department);
		OrganizationData.put("grades", grade);
		OrganizationData.put("banks", bank);
		
		return OrganizationData;
	}

	public void addHistory(Map<String, Object> param, String string) {
		hrManagementMapper.insertHistory(param, string);
	}


}
