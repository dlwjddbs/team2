package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.MaterialMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {
	
	private final MaterialMapper materialMapper;

	public List<Map<String, Object>> getMaterialList(Map<String, Object> map) {
		return materialMapper.getMaterialList(map);
	}

	public int insertMaterial(Map<String, Object> data) {
		int count =  materialMapper.insertMaterial(data);
        
        return count;
	}

	public int updateMaterial(Map<String, Object> data) {
		int count = materialMapper.updateMaterial(data);
        
        return count;
	}

	public int deleteMaterial(Map<String, Object> data) {
		int count = materialMapper.deleteMaterial(data);
        
        return count;
	}

	public List<Map<String, Object>> searchMaterial(String query) {
		return materialMapper.searchMaterial(query);
	}
	
}
