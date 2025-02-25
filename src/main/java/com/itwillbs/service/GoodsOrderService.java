package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.GoodsOrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsOrderService {
	
	private final GoodsOrderMapper goodsOrderMapper;
	
	// 입고 리스트
	public List<Map<String, Object>> getGoodsList(Map<String, Object> map) {
		return goodsOrderMapper.getGoodsList(map);
	}
	
	// 입고 신규버튼 발주 리스트
	public List<Map<String, Object>> getGoodsPoList(Map<String, Object> requestData) {
		List<Map<String, Object>> goodsPoList = goodsOrderMapper.getGoodsPoList(requestData);
		
		return goodsPoList;
	}

	// 입고 저장
	public int insertGoods(List<Map<String, Object>> insertList) {
		int count = 0;
        for (Map<String, Object> data : insertList) {
            count += goodsOrderMapper.insertGoods(data);
        }
        return count;
	}
	
	//입고 수정
	public int updateGoods(List<Map<String, Object>> updateList) {
		int count = 0;
		 
        for (Map<String, Object> data : updateList) {
            count += goodsOrderMapper.updateGoods(data);
        }
        return count;
	}
	
	//입고 삭제
	public int deleteGoods(List<Map<String, Object>> deleteList) {
		int count = 0;
        for (Map<String, Object> data : deleteList) {
            count += goodsOrderMapper.deleteGoods(data);
        }
        return count;
	}
	
	//입고 상세 리스트
	public List<Map<String, Object>> getGoodsDetail(Map<String, Object> map) {
		return goodsOrderMapper.getGoodsDetail(map);
	}

	public int updateDetail(List<Map<String, Object>> updateList) {
		int count = 0;
		 
        for (Map<String, Object> data : updateList) {
            count += goodsOrderMapper.updateDetail(data);
        }
        return count;
	}
	
	// 입고상세 삭제
	public int deleteDetail(List<Map<String, Object>> deleteList) {
		int count = 0;
        for (Map<String, Object> data : deleteList) {
            count += goodsOrderMapper.deletePurchase(data);
        }
        return count;
	}
	
	// 1 입고 상세 상태 변경
	public int insertGoDetail(List<Map<String, Object>> insertList) {
		int count = 0;
        for (Map<String, Object> data : insertList) {
            count += goodsOrderMapper.insertGoDetail(data);
        }
        return count;
	}
	
	// 2️ 발주 상세 상태 변경
    public int updatePoDetail(List<Map<String, Object>> updateList) {
        int count = 0;
        for (Map<String, Object> data : updateList) {
            count += goodsOrderMapper.updatePoDetail(data);
        }
        return count;
    }

    // 3️ 발주 상태변경
    public int updatePurchaseOrderStatus(List<Map<String, Object>> updateList) {
        int count = 0;
        for (Map<String, Object> data : updateList) {
            count += goodsOrderMapper.updatePurchaseOrderStatus(data);
        }
        return count;
    }
    
    // 4 입고  상태 변경
	public int updateGoodsOrderStatus(List<Map<String, Object>> updateList) {
		int count = 0;
        for (Map<String, Object> data : updateList) {
            count += goodsOrderMapper.updateGoodsOrderStatus(data);
        }
        return count;
	}
	
	
}
