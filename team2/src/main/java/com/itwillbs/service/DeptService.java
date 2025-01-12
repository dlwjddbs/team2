package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.DeptMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {
	
	private final DeptMapper DeptMapper;
	
	public List<Map<String, Object>> getUpperDept(Map<String, Object> map) {
		return DeptMapper.getUpperDept(map) ;
	}

}
