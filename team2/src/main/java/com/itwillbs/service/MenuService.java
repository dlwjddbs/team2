package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.MenuMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
	
	private final MenuMapper menuMapper;

	public List<Map<String, Object>> selectMenu(String auth) {
		return menuMapper.selectMenu(auth);
	}

	public List<Map<String, Object>> getMenuList(Map<String, Object> map) {
		return menuMapper.getMenuList(map);
	}

	public Map<String, Object> addMenu(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 메뉴입니다.";
		String resultCode = "0";
		
		try {
			int duplicateCnt = menuMapper.isDuplicateMenuSortOrder(map);
			if (duplicateCnt > 0) {
				result = "중복된 순서입니다.";
				resultCode = "0";
			} else if (menuMapper.isDuplicateMenuURL(map) > 0) {
				result = "중복된 경로입니다.";
				resultCode = "0";
			} else {
				int resultCnt = menuMapper.insertMenu(map);
				if (resultCnt > 0) {
					result = "등록 되었습니다.";
					resultCode = "1";
				} else {
					result = "등록 실패.";
				}
			}
		} catch (Exception e) {
			result = "등록 실패.";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}
}
