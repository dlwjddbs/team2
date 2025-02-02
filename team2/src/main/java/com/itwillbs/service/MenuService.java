package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.controller.MenuController;
import com.itwillbs.repository.MenuMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class MenuService {
	
	private final MenuMapper menuMapper;

	public List<Map<String, Object>> selectMenu(Map<String, String> map) {
		return menuMapper.selectMenu(map);
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

	public Map<String, Object> updateMenu(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "수정 실패";
		String resultCode = "0";
		
		try {
			// 경로 중복 (자기 자신은 제외)
			if (menuMapper.isDuplicateMenuURL(map) > 0) {
				result = "중복된 경로입니다.";
			// 정렬순서 중복 (자기 자신은 제외)
			} else if (menuMapper.isDuplicateMenuSortOrder(map) > 0) {
				result = "중복된 순서입니다.";
			// 하위메뉴 존재 시 폴더는 페이지로 전환 불가
			} else if (!map.get("URL").toString().equals("") && menuMapper.isExistChildMenu(map) > 0) {
				result = "하위 메뉴가 존재하여 페이지로 변환할 수 없습니다.";
			} else {
				if (menuMapper.updateMenu(map) > 0) {
					result = "수정 되었습니다.";
					resultCode = "1";
				}
			}
		} catch (Exception e) {
			log.info(e.toString());
			result = "수정 실패.";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	public Map<String, Object> deleteMenu(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "삭제 실패.";
		String resultCode = "0";
		
		try {
			if (menuMapper.isExistChildMenu(map) > 0) {
				result = "하위 메뉴가 존재합니다.";
			} else {
				if (menuMapper.deleteMenu(map) > 0) {
					result = "삭제 되었습니다.";
					resultCode = "1";
				}
			}
		} catch (Exception e) {
			log.info(e.toString());
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	public Map<String, Object> selectUser(String id) {
		return menuMapper.selectUser(id);
	}

	public List<Map<String, Object>> selectTopMenu(String auth) {
		return menuMapper.selectTopMenu(auth);
	}

	public List<Map<String, Object>> selectMenuCategoryList(Map<String, Object> map) {
		return menuMapper.selectMenuCategoryList(map);
	}

	public Map<String, Object> addMenuCategory(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 카테고리입니다.";
		String resultCode = "0";
		
		try {
			int duplicateCnt = menuMapper.isDuplicateMenuCategorySortOrder(map);
			if (duplicateCnt > 0) {
				result = "중복된 순서입니다.";
				resultCode = "0";
			} else {
				int resultCnt = menuMapper.insertMenuCategory(map);
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

	public Map<String, Object> updateMenuCategory(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "수정 실패";
		String resultCode = "0";
		
		try {
			if (menuMapper.isDuplicateMenuCategorySortOrder(map) > 0) {
				result = "중복된 순서입니다.";
			} else {
				if (menuMapper.updateMenuCategory(map) > 0) {
					result = "수정 되었습니다.";
					resultCode = "1";
				}
			}
		} catch (Exception e) {
			log.info(e.toString());
			result = "수정 실패.";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	public Map<String, Object> deleteMenuCategory(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "삭제 실패.";
		String resultCode = "0";
		
		try {
			if (menuMapper.isExistChildMenuCategory(map) > 0) {
				result = "하위 메뉴가 존재합니다.";
			} else {
				if (menuMapper.deleteMenuCategory(map) > 0) {
					result = "삭제 되었습니다.";
					resultCode = "1";
				}
			}
		} catch (Exception e) {
			log.info(e.toString());
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}
}
