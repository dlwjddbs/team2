package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.AdminLeaveMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminLeaveService {
	
	private final AdminLeaveMapper adminLeaveMapper;

	public List<Map<String, Object>> getLeaveList() {
		return adminLeaveMapper.selectLeaveList();
	}
	
}
