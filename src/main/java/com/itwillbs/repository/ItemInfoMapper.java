package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemInfoMapper {
	// xml의 id값과 동일한 메서드명 사용 필수
	
	List<Map<String, Object>> selectItemInfo();
	
	// 품목코드 중복확인
	int checkDuplicateItemCode(Map<String, Object> map);
	
	// 공통코드 출력
	List<Map<String, Object>> selectMesCommonCode();
	
	// 품목정보 등록
	int insertItemInfo(List<Map<String, Object>> createdRows);

	int updateItemInfo(List<Map<String, Object>> updatedRows);

	int deleteItemInfo(List<String> idList);

	List<Map<String, Object>> selectWhse(Map<String, Object> requestData);



}
