package com.itwillbs.service;

import org.springframework.stereotype.Service;

import com.itwillbs.repository.WarehouseMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class WarehouseService {

	private final WarehouseMapper warehouseMapper;
}
