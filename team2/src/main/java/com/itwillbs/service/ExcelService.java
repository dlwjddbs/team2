package com.itwillbs.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.repository.SalaryMapper;
import com.itwillbs.repository.TestMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class ExcelService {
	
	private final TestMapper testMapper;
	
	public Map<String, Object> selectExcelToastTest() {
		log.info("============= selectExcelToastTest =============");
		
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectToastTest 성공";
		
		try {
			List<Map<String, Object>> testList = testMapper.selectToastTest();
			content.put("contents", testList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	// 엑셀 파일 파싱
	public List<Map<String, Object>> parseExcelFile(MultipartFile file) {
		List<Map<String, Object>> dataList = new ArrayList<>();

		// 엑셀 97 - 2003 까지는 HSSF(xls), 엑셀 2007 이상은 XSSF(xlsx)
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			// 첫 번째 시트 선택, 전체 행 개수 가져오기
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();

			// header 읽기 (2번째 행부터 시작)
			Row headerRow = sheet.getRow(0);
			List<String> headers = new ArrayList<>();

			// 2번째 행의 모든 셀을 읽어서 headers 리스트에 저장
			for (Cell cell : headerRow) {
				headers.add(cell.getStringCellValue());
			}

			// 데이터 읽기 (3번째 행부터)
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);

				if (row == null)
					continue;

				Map<String, Object> rowData = new LinkedHashMap<>();

				// header를 Key로, 셀의 값을 Value로 저장
				for (int j = 0; j < headers.size(); j++) {
					Cell cell = row.getCell(j);
					String header = headers.get(j);

					rowData.put(header, getCellValue(cell));
				}

				dataList.add(rowData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataList;
	}

	// 엑셀 셀 데이터 변환
	// 데이터의 안정성과 일관성을 위해 사용 (데이터 타입이 혼합된 경우 예외 발생 가능성 높음)
	private Object getCellValue(Cell cell) {
		if (cell == null) {
			return ""; // 빈 셀 처리
		}
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue(); // 문자열
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) { // 날짜 포맷이면 변환
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(cell.getDateCellValue());
			}
			return (cell.getNumericCellValue() % 1 == 0) ? (int) cell.getNumericCellValue() // 정수면 int로 변환
					: cell.getNumericCellValue(); // 실수면 그대로 반환
		case BOOLEAN:
			return cell.getBooleanCellValue(); // Boolean (true, false)
		default:
			return ""; // 기타 (날짜 등) 처리
		}
	}
	
	// 엑셀 양식 생성
    public byte[] createExcelTemplate(String tableName) throws IOException {
    	
        List<String> columnNames = testMapper.getColumnNames(tableName);   // 컬럼명 가져오기
        
        log.info("columnNames : " + columnNames);
        
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Template");
            
//            // 컬럼 너비 설정 (단위: 1/256 글자 크기)
//            for (int i = 0; i < columnNames.size(); i++) {
//                sheet.setColumnWidth(i, 5000);  // 5000 = 약 20글자 정도의 너비
//            }
//
//            // row0 - 양식 규칙 (높이 지정)
//            Row row0 = sheet.createRow(0);
//            row0.setHeight((short) 600);  // 높이 지정 (단위: 1/20 포인트)
//            row0.createCell(0).setCellValue("양식 규칙");
            
            // row0 - 양식 규칙 추가
            Row row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("양식 규칙");

            // row1 - 컬럼명 추가
            Row row1 = sheet.createRow(1);
            for (int i = 0; i < columnNames.size(); i++) {
                row1.createCell(i).setCellValue(columnNames.get(i));  // 여기서 바로 추가
            }
            
            // 셀 크기 자동 조정 (각 컬럼의 내용에 맞게 조정)
            for (int i = 0; i < columnNames.size(); i++) {
                sheet.autoSizeColumn(i);  // 자동 조정 적용
            }
            
            // 엑셀 바이너리 변환
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
    
    // 엑셀 수정된 데이터만 db에 업데이트
    public int updateModifiedData(List<Map<String, Object>> uploadedData) {
        int updateCount = 0;

        for (Map<String, Object> newRow : uploadedData) {
            String id = (String) newRow.get("ID");
            log.info("id : " + id);

            if (id == null) {
                continue; // ID가 없으면 업데이트 불가
            }

            // 기존 데이터 조회 (ID 기준)
            Map<String, Object> existingRow = testMapper.selectToastTestById(id);
            log.info("existingRow : " + existingRow.toString());

            // 기존 데이터와 비교하여 변경된 항목이 있는 경우만 업데이트
            if (existingRow != null && !isSameData(existingRow, newRow)) {
            	log.info("update 실행 시작!");
                int result = testMapper.updateToastTestById(newRow);
                updateCount += result;
                log.info("updateCount : " + updateCount);
            }
        }
        
        return updateCount;
    }
    
    // 기존 데이터와 업로드된 데이터 비교
    // 모든 값이 같으면 업데이트 X, 하나라도 다르면 db에 업데이트 실행
    private boolean isSameData(Map<String, Object> existingRow, Map<String, Object> newRow) {
        for (String key : newRow.keySet()) {
            Object oldValue = existingRow.get(key);
            log.info("oldValue : " + oldValue);
            Object newValue = newRow.get(key);
            log.info("newValue : " + newValue);
            
            if (!Objects.equals(oldValue, newValue)) {
            	log.info("다른거 확인됨!");
                return false; // 하나라도 다르면 변경됨
            }
        }
        return true; // 모든 값이 동일하면 변경 없음
    }
    
    // 엑셀 수정된 데이터만 db에 업데이트
    // 현재 그리드에 있는 내용을 db에 업데이트
	public Map<String, Object> updateToastTest(List<Map<String, Object>> updatedRows) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "updateToastTest 성공";
		
		try {
			testMapper.updateToastTest(updatedRows);
		} catch (Exception e) {
			result = false;
			message = "updateToastTest 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
}
