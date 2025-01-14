package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.MenuMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
	
	private final MenuMapper menuMapper;

	public List<Map<String, Object>> selectMenu() {
		return menuMapper.selectMenu();
	}
}
