package com.itwillbs.restController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.service.ExcelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequiredArgsConstructor
@Log
public class ExcelController {

	private final String URL = "/ajax/excelToastTest";

	private final ExcelService excelService;

	@GetMapping(URL)
	public Map<String, Object> getExcelToastTest() {
		log.info("============= getExcelToastTest =============");

		return excelService.selectExcelToastTest();
	}

	@PostMapping(URL)
	public Map<String, Object> insertToastTest(@RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>) requestData.get("createdRows");

		return excelService.insertToastTest(createdRows);
	}

	@DeleteMapping(URL)
	public Map<String, Object> deleteToastTest(@RequestHeader("X-Delete-IDs") String ids) {
		List<String> idList = Arrays.asList(ids.split(","));

		return excelService.deleteToastTest(idList);
	}

	// 엑셀 불러오기
	@PostMapping("/ajax/readExcel")
	public ResponseEntity<List<Map<String, Object>>> readExcel(@RequestParam("file") MultipartFile file) {
		log.info("============= 엑셀 업로드 시작 =============");

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body(Collections.emptyList());
		}

		// 엑셀 파싱 서비스 호출
		List<Map<String, Object>> dataList = excelService.parseExcelFile(file);

		log.info("엑셀 데이터 파싱 완료 : " + dataList);

		// JSON 반환
		return ResponseEntity.ok(dataList);
	}

	// 엑셀 업로드 (수정된 데이터만 DB에 update)
	// 기존 데이터와 비교 후 수정된 데이터만 업데이트, 삽입, 삭제
	@PostMapping("/ajax/modifyExcelData")
	public ResponseEntity<?> modifyExcel(@RequestParam("tableName") String tableName,
										 @RequestParam("tableCodeId") String tableCodeId, 
										 @RequestParam("headerMap") String headerMapJson,
										 @RequestParam("file") MultipartFile file) {
		log.info("============= 엑셀 업로드 시작 (db 수정 동작) =============");

		try {
            // JSON 문자열 → Map 변환
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> headerMap = objectMapper.readValue(headerMapJson, new TypeReference<>() {});
            
            log.info("headerMap : " + headerMap);
            
			// 엑셀 데이터 파싱
			List<Map<String, Object>> uploadedData = excelService.parseExcelFile(file);
			
			int modifiedCount = excelService.modifyExcelData(tableName, tableCodeId, headerMap, uploadedData);

			log.info("modifiedCount : " + modifiedCount);
			
			if (modifiedCount == 0) {
				return ResponseEntity.ok(Map.of("message", "수정된 데이터가 없습니다."));
			} else {
				return ResponseEntity.ok(Map.of("message", modifiedCount + "건의 데이터가 업데이트되었습니다."));
			}	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "업로드 실패"));
		}
	}

	// 엑셀 양식 다운로드
	@PostMapping("/ajax/downloadTemplate")
	public ResponseEntity<byte[]> downloadExcelTemplate(@RequestBody Map<String, Object> requestData) {
		log.info("============= 엑셀 양식 다운로드 시작 =============");

		try {
			String tableName = (String) requestData.get("tableName");
			List<String> headers = (List<String>) requestData.get("headers");

			// 엑셀 양식 생성
			byte[] excelData = excelService.createExcelTemplate(tableName, headers);

			// HTTP 응답 설정
			HttpHeaders headersConfig = new HttpHeaders();
			// headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersConfig.setContentType(
					// MIME 타입 변경 (xlsx)
					MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
			headersConfig.setContentDispositionFormData("attachment", tableName + "_template.xlsx");

			return ResponseEntity.ok().headers(headersConfig).body(excelData);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}