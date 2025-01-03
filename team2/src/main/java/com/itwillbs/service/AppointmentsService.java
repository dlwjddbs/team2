package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.repository.AppointmentsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentsService {
	
    @Autowired
    private AppointmentsMapper appointMapper;
    
    public Map<String, String> getMinMaxDate() {
        return appointMapper.getMinMaxDate();
    }

    public List<Map<String, Object>> getAppointList(Map<String, Object> params) {
        List<Map<String, Object>> results = appointMapper.getAppointList(params);
//        System.out.println("Results: " + results);
        return results;
    }
}
