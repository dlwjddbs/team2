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

	public List<Map<String, Object>> getGoodsList(Map<String, Object> map) {
		return goodsOrderMapper.getGoodsList(map);
	}

	public List<Map<String, Object>> getGoodsPoList(Map<String, Object> requestData) {
		List<Map<String, Object>> goodsPoList = goodsOrderMapper.getGoodsPoList(requestData);
		
		return goodsPoList;
	}

	public int insertGoods(List<Map<String, Object>> insertList) {
		int count = 0;
        for (Map<String, Object> data : insertList) {
            count += goodsOrderMapper.insertGoods(data);
        }
        return count;
	}

	public int updateGoods(List<Map<String, Object>> updateList) {
		int count = 0;
		 
        for (Map<String, Object> data : updateList) {
            count += goodsOrderMapper.updateGoods(data);
        }
        return count;
	}

	public int deleteGoods(List<Map<String, Object>> deleteList) {
		int count = 0;
        for (Map<String, Object> data : deleteList) {
            count += goodsOrderMapper.deleteGoods(data);
        }
        return count;
	}

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

	public int deleteDetail(List<Map<String, Object>> deleteList) {
		int count = 0;
        for (Map<String, Object> data : deleteList) {
            count += goodsOrderMapper.deletePurchase(data);
        }
        return count;
	}

	public int insertGoDetail(List<Map<String, Object>> insertList) {
		int count = 0;
        for (Map<String, Object> data : insertList) {
            count += goodsOrderMapper.insertGoDetail(data);
        }
        return count;
	}

}
