package com.itwillbs.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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

	public Map<String, Object> insertToastTest(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();

		Boolean result = true;
		String message = "insertToastTest 성공";

		try {
			int cnt = testMapper.countExistingIds(createdRows);
			if (cnt > 0) {
				result = false;
				message = "중복된 ToastTest값";
			} else {
				testMapper.insertToastTest(createdRows);
			}
		} catch (Exception e) {
			result = false;
			message = "insertToastTest 실패";
		}

		resultMap.put("result", result);
		resultMap.put("message", message);

		return resultMap;
	}

	public Map<String, Object> deleteToastTest(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();

		Boolean result = true;
		String message = "deleteToastTest 성공";

		try {
			testMapper.deleteToastTest(idList);
		} catch (Exception e) {
			result = false;
			message = "deleteToastTest 실패";
		}

		resultMap.put("result", result);
		resultMap.put("message", message);

		return resultMap;
	}

	// 엑셀 수정된 데이터만 db에 업데이트 및 새로운 데이터 삽입하는 메서드
	public int updateModifiedData(List<Map<String, Object>> uploadedData) {

		if (uploadedData.isEmpty()) {
			return 0; // 데이터가 없으면 처리할 필요 없음
		}

		int updateCount = 0;
		int insertCount = 0;
		int deleteCount = 0;
		int result = 0;
		
		// 유효한 데이터 리스트 생성
		List<Map<String, Object>> validDataList = new ArrayList<>();

		// 업로드된 데이터에서 유효한 데이터만 필터링
		for (Map<String, Object> row : uploadedData) {
		    String id = (String) row.get("ID");
		    // ID가 null이 아니고, 빈 문자열이 아닌 경우
		    if (id != null && !id.trim().isEmpty()) {
		        // ID를 포함한 유효한 데이터 추가
		        validDataList.add(row);
		    }
		}
		
		// 유효한 데이터가 비어 있는지 체크
		if (validDataList.isEmpty()) {
		    return 0; // 유효한 데이터가 없으면 처리할 필요 없음
		}

		// 기존 데이터를 한 번에 조회
		List<Map<String, Object>> existingDataList = testMapper.selectToastTest();
		
		// DB에서 컬럼 타입 조회
	    //Map<String, String> columnTypes = testMapper.getColumnTypes(tableName);
	    
		// 기존 데이터를 Map<ID, Map<String, Object>> 형태로 변환 (조회 속도 개선)
		Map<String, Map<String, Object>> existingDataMap = existingDataList.stream()
				.collect(Collectors.toMap(row -> (String) row.get("ID"), row -> row));

		// 변경된 데이터만 추려서 업데이트, 삽입, 삭제 리스트 생성
		List<Map<String, Object>> dataToUpdate = new ArrayList<>();
		List<Map<String, Object>> dataToInsert = new ArrayList<>();
		List<String> dataToDelete = new ArrayList<>(existingDataMap.keySet()); // 기존 데이터 ID 목록

		for (Map<String, Object> newRow : validDataList) {
			String id = (String) newRow.get("ID");
			Map<String, Object> existingRow = existingDataMap.get(id);
			log.info("existingRow : " + existingRow);

			if (existingRow != null) {
				// 기존 데이터가 있고, 변경된 경우 업데이트 리스트에 추가
				if (!isSameData(existingRow, newRow)) {
//					Map<String, Object> convertedRow = convertDataTypes(newRow, columnTypes);
//			        dataToUpdate.add(convertedRow);
					dataToUpdate.add(newRow);
					log.info("dataToUpdate : " + dataToUpdate);
				}
				// 기존 데이터가 존재하면 삭제 후보에서 제외
				dataToDelete.remove(id);
				log.info("dataToDelete : " + dataToDelete);
			} else {
				// 기존 데이터가 없는 경우 삽입 리스트에 추가
				dataToInsert.add(newRow);
				log.info("dataToInsert : " + dataToInsert);
			}
		}

		// 변경된 데이터만 일괄 업데이트 실행
		if (!dataToUpdate.isEmpty()) {
			result = testMapper.updateToastTestById(dataToUpdate);
			updateCount += result;
			log.info("updateCount : " + updateCount);
		}

		// 새로운 데이터 일괄 삽입 실행
		if (!dataToInsert.isEmpty()) {
			result = testMapper.insertToastTest(dataToInsert);
			insertCount += result;
			log.info("insertCount : " + insertCount);
		}

		// 삭제 실행
		if (!dataToDelete.isEmpty()) {
			result = testMapper.deleteToastTest(dataToDelete);
			deleteCount += result;
			log.info("deleteCount : " + deleteCount);
		}

		// 결과 로깅
		log.info("Total processed rows (updated + inserted + deleted): " + result);
		return updateCount + insertCount + deleteCount;
	}

	// 기존 데이터와 업로드된 데이터 비교 메서드 (변경 여부 체크)
	// 모든 값이 같으면 업데이트 X, 하나라도 다르면 db에 업데이트 실행
	private boolean isSameData(Map<String, Object> oldData, Map<String, Object> newData) {
		for (String key : oldData.keySet()) {
			if (!Objects.equals(oldData.get(key), newData.get(key))) {
				return false;
			}
		}

		return true;
	}
//    private boolean isSameData(Map<String, Object> existingRow, Map<String, Object> newRow) {
//        for (String key : newRow.keySet()) {
//            Object oldValue = existingRow.get(key);
//            log.info("oldValue : " + oldValue);
//            Object newValue = newRow.get(key);
//            log.info("newValue : " + newValue);
//            
//            if (!Objects.equals(oldValue, newValue)) {
//            	log.info("다른거 확인됨!");
//                return false; // 하나라도 다르면 변경됨
//            }
//        }
//        return true; // 모든 값이 동일하면 변경 없음
//    }

	// 엑셀 파일 파싱 메서드
	public List<Map<String, Object>> parseExcelFile(MultipartFile file) {
		List<Map<String, Object>> dataList = new ArrayList<>();

		// 엑셀 97 - 2003 까지는 HSSF(xls), 엑셀 2007 이상은 XSSF(xlsx)
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			// 첫 번째 시트 선택, 전체 행 개수 가져오기
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();

			// header 읽기 (2번째 행부터 시작)
			Row headerRow = sheet.getRow(1);
			List<String> headers = new ArrayList<>();

			// 2번째 행의 모든 셀을 읽어서 headers 리스트에 저장
			for (Cell cell : headerRow) {
				headers.add(cell.getStringCellValue().trim());
			}

			// 데이터 읽기 (3번째 행부터)
			for (int i = 2; i < rowCount; i++) {
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

	// 엑셀 셀 데이터 변환 메서드
	// 데이터의 안정성과 일관성을 위해 사용 (데이터 타입이 혼합된 경우 예외 발생 가능성 높음)
	private Object getCellValue(Cell cell) {
		if (cell == null) {
			return ""; // 빈 셀 처리
		}
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue(); // 문자열
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) { 
				// 날짜 포맷이면 변환
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
	
	// 빈 행인지 확인하는 메서드 
	private boolean isRowEmpty(Row row) {
	    for (Cell cell : row) {
	        if (cell != null && cell.getCellType() != CellType.BLANK && !getCellValue(cell).toString().trim().isEmpty()) {
	            return false; // 하나라도 값이 있으면 빈 행이 아님
	        }
	    }
	    return true; // 모든 셀이 비어있으면 빈 행
	}

	// 엑셀 양식 생성 메서드
	public byte[] createExcelTemplate(String tableName, List<String> headers) throws IOException {
				
//		List<String> columnNames = testMapper.getColumnNames(tableName); // 컬럼명 가져오기
//
//		log.info("columnNames : " + columnNames);
		
		log.info("headers : " + headers);

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Template");
			
//            // row0 - 양식 규칙 (높이 지정)
//            Row row0 = sheet.createRow(0);
//            row0.setHeight((short) 600);  // 높이 지정 (단위: 1/20 포인트)
//            row0.createCell(0).setCellValue("양식 규칙");

			// row0 - 양식 규칙 추가
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue("양식 규칙 (이 부분은 지우고 사용해주세요)");
			
		    // 셀 병합 (A1 ~ D1)
		    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

		    // 병합된 셀에 내용 추가
		    Cell cell = row0.createCell(0);
		    cell.setCellValue("양식 규칙 (이 부분은 지우고 사용해주세요)");

		    // 스타일 적용 (선택 사항)
		    CellStyle style = sheet.getWorkbook().createCellStyle();
		    style.setAlignment(HorizontalAlignment.CENTER); // 가운데 정렬
		    style.setVerticalAlignment(VerticalAlignment.CENTER); // 수직 가운데 정렬

		    Font font = sheet.getWorkbook().createFont();
		    font.setBold(true); // 글씨 굵게
		    style.setFont(font);

		    cell.setCellStyle(style);
		    
			// row1 - Grid에서 전달한 header 추가 (컬럼명 추가)
			Row row1 = sheet.createRow(1);
			for (int i = 0; i < headers.size(); i++) {
				row1.createCell(i).setCellValue(headers.get(i)); // 여기서 바로 추가
			}
			
			// 열 너비 설정 (단위: 1/256 * 글자 크기)
			for (int i = 0; i < headers.size(); i++) {
				sheet.setColumnWidth(i, 5000);	// 5000 = 약 20글자 정도의 너비
				//sheet.autoSizeColumn(i);	// (각 컬럼의 내용에 맞게 셀 크기 자동 조정)
			}

			// 엑셀 바이너리 변환
			workbook.write(outputStream);
			return outputStream.toByteArray();
		}
	}

	// 엑셀 데이터 타입 변환 메서드 (미구현)
	// 테이블 컬럼 타입을 기반으로 변환 적용
	// 현재 테이블 이름을 어떻게 찾을건지 생각해야함 
	public Map<String, Object> convertDataTypes(Map<String, Object> rowData, Map<String, String> columnTypes) {
		Map<String, Object> convertedRow = new HashMap<>();

		for (Map.Entry<String, Object> entry : rowData.entrySet()) {
			String columnName = entry.getKey();
			Object value = entry.getValue();
			String dataType = columnTypes.get(columnName);

			if (dataType == null) {
				convertedRow.put(columnName, value);
				continue;
			}

			switch (dataType.toUpperCase()) {
			case "NUMBER":
				convertedRow.put(columnName, value != null ? new BigDecimal(value.toString()) : null);
				break;
			case "DATE":
			case "TIMESTAMP":
				convertedRow.put(columnName,
						value != null
								? LocalDateTime.parse(value.toString(),
										DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
								: null);
				break;
			case "VARCHAR2":
			default:
				convertedRow.put(columnName, value);
				break;
			}
		}

		return convertedRow;
	}

	// 엑셀 수정된 데이터만 db에 업데이트하는 메서드 (미구현)
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
