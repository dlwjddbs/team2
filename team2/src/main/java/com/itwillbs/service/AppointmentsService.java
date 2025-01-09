package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    public List<Map<String, Object>> getAllDepartments() {
        return appointMapper.getAllDepartments();
    }
    
    public List<Map<String, Object>> getAllRanks(){
    	return appointMapper.getAllRanks();
    }
    
    public List<Map<String, Object>> getOrgTree() {
        return appointMapper.getOrgTree(); 
    }
    
    public void updateAppointments(List<Map<String, Object>> employeeData) {
        for (Map<String, Object> employee : employeeData) {
        	
        	System.out.println("OLD_DEPT: " + employee.get("OLD_DEPT"));
        	
            String assignmentType = (String) employee.get("ASSIGNMENT_TYPE");

            // OLD_DEPT 처리 (입사 시)
            if ("입사".equals(assignmentType) && employee.get("OLD_DEPT") == null) {
                employee.put("OLD_DEPT", "");
            }
            if ("입사".equals(assignmentType) && employee.get("OLD_GRADE") == null) {
                employee.put("OLD_GRADE", ""); // 빈 문자열로 대체
            }

            // MEMBER_HISTORY 삽입
            appointMapper.insertMemberHistory(employee);

            if ("퇴사".equals(assignmentType)) {
                // 퇴사 처리
                appointMapper.updateMemberForResign(
                    (String) employee.get("MEMBER_ID"),
                    (String) employee.get("CHANGE_DATE")
                );
            } else {
                // 일반 발령 처리
                appointMapper.updateMember(employee);
            }
        }
    }
    
    public void updateChangedAppointments(List<Map<String, Object>> employeeData) {
        for (Map<String, Object> employee : employeeData) {
            System.out.println("HISTORY_ID: " + employee.get("HISTORY_ID"));

            appointMapper.updateChangedColumns(employee);
        }
    }

    public int deleteHistories(List<String> historyIds) {
        int deletedCount = 0;

        for (String historyId : historyIds) {
            deletedCount += appointMapper.deleteHistoryById(historyId);
        }

        return deletedCount;
    }
}
