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

}
