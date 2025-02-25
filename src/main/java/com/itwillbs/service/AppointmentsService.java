package com.itwillbs.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.repository.AppointmentsMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentsService {
	
    @Autowired
    private AppointmentsMapper appointMapper;
    
    public Map<String, String> getMinMaxDate() {
        return appointMapper.getMinMaxDate();
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
    
    // 조회
    public List<Map<String, Object>> getAppointList(Map<String, Object> params) {
    	List<Map<String, Object>> results = appointMapper.getAppointList(params);
    	return results;
    }
    
    // 등록
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
    
    // 수정저장
    public void updateChangedAppointments(List<Map<String, Object>> employeeData) {
        for (Map<String, Object> employee : employeeData) {
            System.out.println("HISTORY_ID: " + employee.get("HISTORY_ID"));

            appointMapper.updateChangedColumns(employee);
        }
    }
        
    //삭제
    @Transactional
    public int deleteHistories(List<String> historyIds) {
        int totalDeleted = 0;

        for (String historyId : historyIds) {
            System.out.println("Processing historyId: " + historyId);

            Map<String, Object> memberData = appointMapper.getMemberDataByHistoryId(historyId);
            System.out.println("Fetched memberData: " + memberData);

            if (memberData != null) {
                String memberId = (String) memberData.get("MEMBER_ID");
                String oldDept = (String) memberData.get("OLD_DEPT");
                String oldGrade = (String) memberData.get("OLD_GRADE");
                String assignmentType = (String) memberData.get("ASSIGNMENT_TYPE");
                String todayDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                if ("입사".equals(assignmentType)) {
                    System.out.printf("Resigning MEMBER_ID=%s with CHANGE_DATE=%s%n", memberId, todayDate);
                    appointMapper.updateMemberForResign(memberId, todayDate);
                }

                appointMapper.restoreMemberData(memberId, oldDept, oldGrade);
                System.out.printf("Restored member data: MEMBER_ID=%s, DEPT_ID=%s, GRADE_ID=%s%n", memberId, oldDept, oldGrade);
            }

            totalDeleted += appointMapper.deleteHistoryById(historyId);
        }

        System.out.println("Total deleted records: " + totalDeleted);
        return totalDeleted;
    }

}
