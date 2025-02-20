package com.itwillbs.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.ManufactureMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManufactureService {
	
	private final ManufactureMapper manufactureMapper;

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyWorkcenter(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyToastTest 성공";
		
		try {
			if (createdRows.size() > 0) {
				manufactureMapper.insertWorkcenter(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				manufactureMapper.updateWorkcenter(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyToastTest 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteWorkcenter(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteWorkcenter 성공";
		
		try {
			if (idList.size() > 0) {
				manufactureMapper.deleteWorkcenter(idList);
				manufactureMapper.deleteEquipmentByWorkcenterId(idList);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteWorkcenter 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> insertEquipment(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		Boolean result = true;
		String message = "insertEquipment 성공";
		
		try {
			manufactureMapper.insertEquipment(createdRows);
		} catch (Exception e) {
			result = false;
			message = "insertEquipment 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> deleteEquipment(List<String> equipmentIds) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteEquipment 성공";
		
		try {
			if (equipmentIds.size() > 0) {
				manufactureMapper.deleteEquipment(equipmentIds);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteEquipment 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> checkDuplicateCode(Map<String, Object> map) {
		Map<String, Object> message = new HashMap<>();
		
		String result = "중복된 코드입니다.";
		String resultCode = "0";
		
		try {
			int resultCnt = manufactureMapper.checkDuplicateCode(map);
			if (resultCnt == 0) {
				result = "사용가능한 코드입니다.";
				resultCode = "1";
			}
		} catch (Exception e) {
			result = "조회 실패. 재시도 하세요.";
			resultCode = "0";
		}
		
		message.put("result", result);
		message.put("resultCode", resultCode);
		
		return message;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyProcess(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyProcess 성공";
		
		try {
			if (createdRows.size() > 0) {
				manufactureMapper.insertProcess(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				manufactureMapper.updateProcess(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyProcess 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> deleteProcess(List<String> processIds) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteProcess 성공";
		
		try {
			if (processIds.size() > 0) {
				manufactureMapper.deleteProcess(processIds);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteProcess 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyRouting(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyRouting 성공";
		
		try {
			if (createdRows.size() > 0) {
				manufactureMapper.insertRouting(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				manufactureMapper.updateRouting(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyRouting 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteRouting(List<String> processIds) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteRouting 성공";
		
		try {
			if (processIds.size() > 0) {
				manufactureMapper.deleteRouting(processIds);
				manufactureMapper.deleteRoutingSequence(processIds);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteRouting 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertRoutingSequence(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		Boolean result = true;
		String message = "insertRoutingSequence 성공";
		
		try {
			if (createdRows.size() > 0) {
				manufactureMapper.insertRoutingSequence(createdRows);
			
				Map<String, Object> map = createdRows.get(0);
				map.put("createdRowsCnt", createdRows.size());
				manufactureMapper.updateRoutingProcessQuantity(map);
			}
		} catch (Exception e) {
			result = false;
			message = "insertRoutingSequence 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}


	public Map<String, Object> modifyProductionOrder(Map<String, Object> requestData) {
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyProductionOrder 성공";
		
		try {
			if (createdRows.size() > 0) {
	 			int max_id = manufactureMapper.selectTodayMaxProductionOrderId();

	 			LocalDate now = LocalDate.now();
	 			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	 			String formatedNow = now.format(formatter);
	 			
	 			for (Map<String, Object> row : createdRows) {
	 				String productionOrderId = "PRO-" + formatedNow + "-" + String.format("%04d", max_id);
	 				row.replace("PRODUCTION_ORDER_ID", productionOrderId);
	 				
	 				max_id++;
	 			}
				
				manufactureMapper.insertProductionOrder(createdRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyProductionOrder 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertProductionOrderDetail(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "insertProductionOrderDetail 성공";
		
 		try {
 			if (createdRows.size() > 0) {
	 			int max_id = manufactureMapper.selectTodayMaxProductionOrderDetailId();
	
	 			LocalDate now = LocalDate.now();
	 			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	 			String formatedNow = now.format(formatter);
	 			
	 			for (Map<String, Object> row : createdRows) {
	 				String productionOrderDetailId = "PROD-" + formatedNow + "-" + String.format("%04d", max_id);
	 				row.replace("PRODUCTION_ORDER_DETAIL_ID", productionOrderDetailId);
	 				
	 				max_id++;
	 			}
	 			
	 			List<Map<String, String>> bomList = manufactureMapper.selectProductionOrderDetailBOM(createdRows);
				manufactureMapper.insertProductionOrderDetail(createdRows);
				
				String productionOrderId = createdRows.getFirst().get("PRODUCTION_ORDER_ID").toString();
				manufactureMapper.updateProductionOrderCnt(productionOrderId);
	 			
	 			int put_materials_max_id = manufactureMapper.selectMaxPutMaterialsId();
	 			
	 			for (Map<String, String> row : bomList) {
	 				String putMaterialsId = "PM" + String.format("%06d", put_materials_max_id);
	 				row.put("PUT_MATERIALS_ID", putMaterialsId);
	 				
	 				put_materials_max_id++;
	 			}
				
				manufactureMapper.insertProductionOrderDetailBOM(bomList);
 			}
		} catch (Exception e) {
			result = false;
			message = "insertProductionOrderDetail 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteProductionOrder(List<String> productionOrderIds) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteProductionOrder 성공";
		
		try {
			if (productionOrderIds.size() > 0) {
				manufactureMapper.deleteProductionOrder(productionOrderIds);
				
				List<String> productionOrderDetailIds = manufactureMapper.selectProductionOrderDetailDeleteIds(productionOrderIds);
				manufactureMapper.deleteProductionOrderDetail(productionOrderIds);
				
				if (productionOrderDetailIds.size() > 0) {
					manufactureMapper.deletePutMaterials(productionOrderDetailIds);
				}
			}
		} catch (Exception e) {
			result = false;
			message = "deleteProductionOrder 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> modifyBom(Map<String, Object> requestData) {
		List<Map<String, Object>> updatedRows = (List<Map<String, Object>>)requestData.get("updatedRows");
		List<Map<String, Object>> createdRows = (List<Map<String, Object>>)requestData.get("createdRows");

		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "modifyBom 성공";
		
		try {
			if (createdRows.size() > 0) {
				manufactureMapper.insertBom(createdRows);
			}
			
			if (updatedRows.size() > 0) {
				manufactureMapper.updateBom(updatedRows);
			}
		} catch (Exception e) {
			result = false;
			message = "modifyBom 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteBom(List<String> idList) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "deleteBom 성공";
		
		try {
			if (idList.size() > 0) {
				manufactureMapper.deleteBom(idList);
				manufactureMapper.deleteBomDetailById(idList);
			}
		} catch (Exception e) {
			result = false;
			message = "deleteBom 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertBomDetail(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "insertBomDetail 성공";
		
		try {
			if (createdRows.size() > 0) {
				manufactureMapper.insertBomDetail(createdRows);
			
				Map<String, Object> map = createdRows.get(0);
				map.put("createdRowsCnt", createdRows.size());
				
				manufactureMapper.updateBomQuantity(map);
			}
			
		} catch (Exception e) {
			result = false;
			message = "insertBomDetail 실패";
			
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
}
