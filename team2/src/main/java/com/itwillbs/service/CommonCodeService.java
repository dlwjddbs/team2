package com.itwillbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.CommonCodeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonCodeService {
	
	private final CommonCodeMapper commonCodeMapper;
	
    public List<Map<String, Object>> getCommonCodes() {
        List<Map<String, Object>> rawData = commonCodeMapper.selectCommonCode();

        // GROUP_ID 기준으로 그룹화
        Map<String, Map<String, Object>> groupedData = new LinkedHashMap<>();

        for (Map<String, Object> row : rawData) {
            String groupId = row.get("GROUP_ID").toString();

            // 그룹이 처음 등장하면 초기화
            if (!groupedData.containsKey(groupId)) {
                Map<String, Object> group = new LinkedHashMap<>();
                group.put("GROUP_ID", groupId);
                group.put("GROUP_NAME", row.get("GROUP_NAME"));
                group.put("COMMON_CODE", new ArrayList<Map<String, Object>>());
                groupedData.put(groupId, group);
            }

            // COMMON_CODE 추가
            Map<String, Object> code = new LinkedHashMap<>();
            code.put("CODE_ID", row.get("CODE_ID"));
            code.put("CODE_NAME", row.get("CODE_NAME"));
            code.put("USE_YN", row.get("USE_YN"));

            ((List<Map<String, Object>>) groupedData.get(groupId).get("COMMON_CODE")).add(code);
        }
        return new ArrayList<>(groupedData.values());
    }

	public List<Map<String, Object>> getGroupCommonCode(Map<String, Object> map) {
		return commonCodeMapper.getGroupCommonCode(map);
	}


	public Map<String, Object> insertCommonCodeGroup(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "이미 등록된 코드입니다.";
		String resultCode = "0";
		
		try {
			int duplicateCnt = commonCodeMapper.isDuplicateCommonCodeGroup(map);
			if (duplicateCnt == 0) {
				int resultCnt = commonCodeMapper.insertCommonCodeGroup(map);
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

	public Map<String, Object> deleteCommonCodeGroup(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "";
		String resultCode = "";
		
		try {
			int resultCnt = commonCodeMapper.deleteCommonCodeGroup(map);
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

	public Map<String, Object> updateCommonCodeGroup(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "";
		String resultCode = "";
		
		try {
			int resultCnt = commonCodeMapper.updateCommonCodeGroup(map);
			if (resultCnt > 0) {
				result = "수정 되었습니다.";
				resultCode = "1";
			}
		} catch (Exception e) {
			result = "수정 실패.";
			resultCode = "0";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}
}
