package com.itwillbs.restController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	private final ExcelService excelService;
	
    @PostMapping("/salary/uploadExcel")
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
	
}