package com.itwillbs.restController;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.service.ExcelService;
import com.itwillbs.service.SalaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequiredArgsConstructor
@Log
public class ExcelController {

	private final ExcelService excelService;
	
	@GetMapping("/ajax/excelToastTest")
	public Map<String, Object> getExcelToastTest() {
		log.info("============= getExcelToastTest =============");

		return excelService.selectExcelToastTest();
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