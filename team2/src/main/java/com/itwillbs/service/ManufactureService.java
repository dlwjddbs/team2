package com.itwillbs.service;

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
	
	public Map<String, Object> selectWorkcenter() {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectWorkcenter 성공";
		
		try {
			List<Map<String, Object>> workcenterList = manufactureMapper.selectWorkcenter();
			content.put("contents", workcenterList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectWorkcenter 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

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
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> selectMember(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectMember 성공";
		
		try {
			List<Map<String, Object>> workcenterList = manufactureMapper.selectMember(requestData);
			content.put("contents", workcenterList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectMember 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> selectEquipment(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectEquipment 성공";
		
		try {
			List<Map<String, Object>> workcenterList = manufactureMapper.selectEquipment(requestData);
			content.put("contents", workcenterList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectEquipment 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> addEquipment(List<Map<String, Object>> createdRows) {
		Map<String, Object> resultMap = new HashMap<>();
		Boolean result = true;
		String message = "addEquipment 성공";
		
		try {
			manufactureMapper.addEquipment(createdRows);
		} catch (Exception e) {
			result = false;
			message = "addEquipment 실패";
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

	public Map<String, Object> selectProcess(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectProcess 성공";
		
		try {
			List<Map<String, Object>> processList = manufactureMapper.selectProcess(requestData);
			content.put("contents", processList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectProcess 실패";
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

	public Map<String, Object> selectRouting(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectRouting 성공";
		
		try {
			List<Map<String, Object>> processList = manufactureMapper.selectRouting(requestData);
			content.put("contents", processList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectRouting 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> selectItem(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectItem 성공";
		
		try {
			List<Map<String, Object>> processList = manufactureMapper.selectItem(requestData);
			content.put("contents", processList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectItem 실패";
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
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public Map<String, Object> selectRoutingSequence(Map<String, Object> requestData) {
		Map<String, List<Map<String, Object>>> content = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		
		Boolean result = true;
		String message = "selectRoutingSequence 성공";
		
		try {
			List<Map<String, Object>> processList = manufactureMapper.selectRoutingSequence(requestData);
			content.put("contents", processList);
			resultMap.put("data", content);
		} catch (Exception e) {
			result = false;
			message = "selectRoutingSequence 실패";
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
			System.out.println(e);
			result = false;
			message = "insertRoutingSequence 실패";
		}
		
		resultMap.put("result", result);
		resultMap.put("message", message);
		
		return resultMap;
	}
	
}
