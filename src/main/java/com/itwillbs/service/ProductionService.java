package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.ProductionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionService {
	
	private final ProductionMapper productionMapper;

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertMaterialsDetail(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		Boolean result = true;
		String message = "insertMaterialsDetail 성공";
		
		try {
			if (createdRows.size() > 0) {
				int put_materials_detail_max_id = productionMapper.selectMaxPutMaterialsDetailId();
				for (Map<String, Object> row : createdRows) {
					String putMaterialsDetailId = "PMD" + String.format("%06d", put_materials_detail_max_id);
				
					row.put("PUT_MATERIALS_DETAIL_ID", putMaterialsDetailId);
					
					put_materials_detail_max_id++;
				}
				
				Map<String, Object> map = createdRows.getFirst();
				
//				1. put_materials_detail LOT연결
				productionMapper.insertMaterialsDetail(createdRows);
				
//				2. inbound_lot, production_lot 개수차감
				productionMapper.updateLotQuantity(createdRows);
				
//				3. item_location 개수차감
				productionMapper.updateWarehouseItemQuantity(createdRows);
				
//				4. workcenter_log 시작시간 추가
				productionMapper.insertWorkcenterLog(map);
				
//				5. 작업지시 상세 진행상태 변경
				productionMapper.updateProductionOrderDetailStatus(map);
				
//				6. 작업지시 진행상태 변경 
				productionMapper.updateProductionOrderStatus(map);
			}
		} catch (Exception e) {
			result = false;
			message = "insertMaterialsDetail 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
}
