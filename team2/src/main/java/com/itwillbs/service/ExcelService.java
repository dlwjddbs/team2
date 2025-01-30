package com.itwillbs.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class ExcelService {
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
				headers.add(cell.getStringCellValue());
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
}
