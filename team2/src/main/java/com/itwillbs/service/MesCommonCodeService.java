package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.MesCommonCodeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesCommonCodeService {

	private final MesCommonCodeMapper mesCommonCodeMapper;

	public List<Map<String, Object>> getGroupMesCommonCode(Map<String, Object> map) {
		return mesCommonCodeMapper.getGroupMesCommonCode(map);
	}

	public Map<String, Object> insertMesCommonCodeGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
