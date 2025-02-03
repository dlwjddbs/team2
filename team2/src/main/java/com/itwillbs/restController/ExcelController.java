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
	    List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");
		
        return excelService.insertToastTest(createdRows);
	}	
	
	@DeleteMapping(URL)
	public Map<String, Object> deleteToastTest(@RequestHeader("X-Delete-IDs") String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        
        return excelService.deleteToastTest(idList);
	}
	
	// 엑셀 업로드 (수정된 데이터만 DB에 update)
	// 기존 데이터와 비교 후 수정된 데이터만 업데이트
    // 현재 그리드에 있는 내용을 db에 업데이트
	@PutMapping(URL)
	public Map<String, Object> updateToastTest(@RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		
		return excelService.updateToastTest(updatedRows);
	}	
	
	// 엑셀 업로드
	@PostMapping("/ajax/uploadExcel")
	public ResponseEntity<List<Map<String, Object>>> uploadExcel(@RequestParam("file") MultipartFile file) {
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
	// 기존 데이터와 비교 후 수정된 데이터만 업데이트
	@PostMapping("/ajax/updateExcelData")
	public ResponseEntity<?> updateExcelData(@RequestParam("file") MultipartFile file) {
		log.info("============= 엑셀 업로드 시작 (db 수정 동작) =============");
		
	    try {
	    	 // 엑셀 데이터 파싱
	        List<Map<String, Object>> uploadedData = excelService.parseExcelFile(file);
	        
	        int updatedCount = excelService.updateModifiedData(uploadedData);
	        
	        return ResponseEntity.ok(Map.of("message", updatedCount + "건의 데이터가 업데이트되었습니다."));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "업로드 실패"));
	    }
	    
//		int updatedCount = excelService.updateModifiedData(modifiedRows);
    
//		return ResponseEntity.ok(Map.of("message", updatedCount + "건의 데이터가 업데이트되었습니다."));
	}

	// 엑셀 양식 다운로드
	@GetMapping("/ajax/downloadTemplate")
	public ResponseEntity<byte[]> downloadExcelTemplate(@RequestParam("tableName") String tableName) {
		log.info("============= 엑셀 양식 다운로드 시작 =============");

		try {
			// 엑셀 양식 생성
			byte[] excelData = excelService.createExcelTemplate(tableName);

			// HTTP 응답 설정
			HttpHeaders headers = new HttpHeaders();
			// headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentType(
					MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")); // MIME 타입 변경 (xlsx)
			headers.setContentDispositionFormData("attachment", tableName + "_template.xlsx");

			return ResponseEntity.ok().headers(headers).body(excelData);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
}