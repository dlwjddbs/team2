package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.MesCommonCodeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class MesCommonCodeService {

	private final MesCommonCodeMapper mesCommonCodeMapper;

	public List<Map<String, Object>> getGroupMesCommonCode(Map<String, Object> map) {
		return mesCommonCodeMapper.getGroupMesCommonCode(map);
	}
	

	public Map<String, Object> insertMesCommonCodeGroup(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 코드입니다.";
		String resultCode = "0";
		
		try {
			int duplicateCnt = mesCommonCodeMapper.isDuplicateMesCommonCodeGroup(map);
			int duplicateCnt2 = mesCommonCodeMapper.isDuplicateMainOrder(map); // 중복된 순서 확인
			
			if (duplicateCnt2 > 0) {
	            result = "중복된 순서입니다.";

			} else if (duplicateCnt == 0) {
				int resultCnt = mesCommonCodeMapper.insertMesCommonCodeGroup(map);
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


	public Map<String, Object> deleteMesCommonCodeGroup(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "하위코드가 존재합니다.";
		String resultCode = "0";
		
		try {
			int existCnt = mesCommonCodeMapper.isExistMesCommonCodeGroupChild(map);
			if (existCnt == 0) {
				int resultCnt = mesCommonCodeMapper.deleteMesCommonCodeGroup(map);
				if (resultCnt > 0) {
					result = "삭제 되었습니다.";
					resultCode = "1";
				}
			}
		} catch (Exception e) {
			result = "삭제 실패.";
			resultCode = "0";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}


	public Map<String, Object> updateMesCommonCodeGroup(Map<String, Object> map) {
		
		
	    Map<String, Object> message = new HashMap<>();

	    String result = "수정 실패.";
	    String resultCode = "0";

	    try {
	        // 중복된 순서 체크
	        int duplicateCnt = mesCommonCodeMapper.isDuplicateMainOrder(map);
	        if (duplicateCnt > 0) {
	            result = "중복된 순서입니다.";
	        } else {
	            int resultCnt = mesCommonCodeMapper.updateMesCommonCodeGroup(map);
	            if (resultCnt > 0) {
	                result = "수정 되었습니다.";
	                resultCode = "1";
	            }
	        }
	    } catch (Exception e) {
	        result = "수정 실패.";
	        resultCode = "0";
	    }

	    message.put("result", result);
	    message.put("resultCode", resultCode);

	    return message;
	}


	public List<Map<String, Object>> getMesCommonCode(Map<String, Object> map) {
		return mesCommonCodeMapper.getMesCommonCode(map);
	}


	public Map<String, Object> insertMesCommonCode(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 코드입니다.";
		String resultCode = "0";
		
		try {
			int duplicateCnt = mesCommonCodeMapper.isDuplicateMesCommonCode(map);
			int duplicateCnt2 = mesCommonCodeMapper.isDuplicateSubOrder(map); // 중복된 순서 확인
			
			if (duplicateCnt2 > 0) {
	            result = "중복된 순서입니다.";
	        } else if (duplicateCnt == 0) {
				int resultCnt = mesCommonCodeMapper.insertMesCommonCode(map);
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


	public Map<String, Object> deleteMesCommonCode(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "";
		String resultCode = "";
		
		try {
			int resultCnt = mesCommonCodeMapper.deleteMesCommonCode(map);
			if (resultCnt > 0) {
				result = "삭제 되었습니다.";
				resultCode = "1";
			}
		} catch (Exception e) {
			result = "삭제 실패.";
			resultCode = "0";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}


	public Map<String, Object> updateMesCommonCode(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
	    String result = "수정 실패.";
	    String resultCode = "0";
		
	    try {
	        // 중복된 순서 체크
	        int duplicateCnt = mesCommonCodeMapper.isDuplicateSubOrder(map);
	        log.info(String.valueOf(duplicateCnt));
	        if (duplicateCnt > 0) {
	            result = "중복된 순서입니다.";
	        } else {
	            int resultCnt = mesCommonCodeMapper.updateMesCommonCode(map);
	            if (resultCnt > 0) {
	                result = "수정 되었습니다.";
	                resultCode = "1";
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	        result = "수정 실패.";
	        resultCode = "0";
	    }

	    message.put("result", result);
	    message.put("resultCode", resultCode);

	    return message;
	}
	
	
	
}
