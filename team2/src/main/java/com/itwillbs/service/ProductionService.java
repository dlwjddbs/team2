package com.itwillbs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.ProductionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionService {
	
	private final ProductionMapper productionMapper;

	
}
