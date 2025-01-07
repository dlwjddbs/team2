package com.itwillbs.service;

import java.util.ArrayList;
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
}
